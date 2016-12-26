package showcharacter;

public class PrintCharacter {
	
	public static void print(int[] info){
		for (int i = 0; i < info.length; i++) {
			if(info[i]>0){
				for (int j = 0; j < info[i]; j++) {
					System.out.print("    *");
				}
			}else if(info[i]==0){
				for (int j = 0; j < info[i+1]; j++) {
					System.out.print("     ");
				}
				i+=1;
			}else if(info[i]<0){
				System.out.print("\n");
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		CharacterCode characterCode = new CharacterCode();
		int[] code= characterCode.A;
		print(code);
	}

}
