package lizheng.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.core.search.aggregation.SumAggregation;
import io.searchbox.indices.Analyze;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.range.date.DateRangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class JestService {


    @Autowired
    JestClient jestClient;

    /**
     * 创建索引
     *
     * @param indexName
     * @return
     * @throws Exception
     */
    public boolean createIndex(String indexName) throws Exception {

        JestResult jr = jestClient.execute(new CreateIndex.Builder(indexName).build());
        return jr.isSucceeded();
    }

    /**
     * Put映射
     *
     * @param indexName
     * @param typeName
     * @param source
     * @return
     * @throws Exception
     */
    public boolean createIndexMapping(String indexName, String typeName, String source) throws Exception {

        PutMapping putMapping = new PutMapping.Builder(indexName, typeName, source).build();
        JestResult jr = jestClient.execute(putMapping);
        return jr.isSucceeded();
    }

    /**
     * Get映射
     *
     * @param indexName
     * @param typeName
     * @return
     * @throws Exception
     */
    public String getIndexMapping(String indexName, String typeName) throws Exception {

        GetMapping getMapping = new GetMapping.Builder().addIndex(indexName).addType(typeName).build();
        JestResult jr = jestClient.execute(getMapping);
        return jr.getJsonString();
    }

    /**
     * 索引文档
     *
     * @param indexName
     * @param typeName
     * @param objs
     * @return
     * @throws Exception
     */
    public boolean index(String indexName, String typeName, List<Object> objs) throws Exception {

        Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
        for (Object obj : objs) {
            Index index = new Index.Builder(obj).build();
            bulk.addAction(index);
        }
        BulkResult br = jestClient.execute(bulk.build());
        return br.isSucceeded();
    }

    /**
     * 搜索文档
     *
     * @param indexName
     * @param typeName
     * @param query
     * @return
     * @throws Exception
     */
    public SearchResult search(String indexName, String typeName, String query) throws Exception {

        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        return jestClient.execute(search);
    }

    /**
     * Count文档
     *
     * @param indexName
     * @param typeName
     * @param query
     * @return
     * @throws Exception
     */
    public Double count(String indexName, String typeName, String query) throws Exception {

        Count count = new Count.Builder()
                .addIndex(indexName)
                .addType(typeName)
                .query(query)
                .build();
        CountResult results = jestClient.execute(count);
        return results.getCount();
    }

    /**
     * Get文档
     *
     * @param indexName
     * @param typeName
     * @param id
     * @return
     * @throws Exception
     */
    public JestResult get(String indexName, String typeName, String id) throws Exception {

        Get get = new Get.Builder(indexName, id).type(typeName).build();
        return jestClient.execute(get);
    }

    /**
     * Delete索引
     *
     * @param indexName
     * @return
     * @throws Exception
     */
    public boolean delete(String indexName) throws Exception {

        JestResult jr = jestClient.execute(new DeleteIndex.Builder(indexName).build());
        return jr.isSucceeded();
    }

    /**
     * Delete文档
     *
     * @param indexName
     * @param typeName
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(String indexName, String typeName, String id) throws Exception {

        DocumentResult dr = jestClient.execute(new Delete.Builder(id).index(indexName).type(typeName).build());
        return dr.isSucceeded();
    }


    public String analyze(String text) {
        try {
            JestResult jr = jestClient.execute(new Analyze.Builder().text(text).build());
            return jr.getJsonString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 关闭JestClient客户端
     *
     * @throws Exception
     */
    public void closeJestClient() throws Exception {

        if (jestClient != null) {
            jestClient.shutdownClient();
        }
    }


    public String generateSearchString() {

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();

        BoolQueryBuilder parentQuery = QueryBuilders.boolQuery();
        //parentQuery.filter(QueryBuilders.existsQuery("asr_agent"));
        parentQuery.filter(QueryBuilders.matchAllQuery());


        // 构建agg对象，默认返回前100条记录
        SumAggregationBuilder agg = AggregationBuilders.sum("total_bridge_duration").field
                ("bridge_duration");

        Script script = new Script("scriptId");

        DateHistogramAggregationBuilder interval = AggregationBuilders.dateHistogram("dateHistogram").field
                ("end_time").dateHistogramInterval(new DateHistogramInterval("day"));
        interval.subAggregation(agg);


        searchSourceBuilder.query(parentQuery).from(0).size(1024).aggregation(interval);
        System.out.println(searchSourceBuilder.toString());

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("cdr_3000000_201708")
                .ignoreUnavailable(true).allowNoIndices(true).build();

        SearchResult searchResult = null;
        try {
            searchResult = this.jestClient.execute(search);
        } catch (IOException e) {
            System.out.println(e);
        }

        String jsonString = searchResult.getJsonString();

        JSONObject fastResult = JSON.parseObject(jsonString);
        JSONObject aggregations = fastResult.getJSONObject("aggregations");

        return aggregations.toJSONString();
    }
}
