class Solution {
    public String removeOccurrences(String s, String part) {
      StringBuilder result=new StringBuilder();
      for(char ch:s.toCharArray()){
        result.append(ch);
        if(result.length()>=part.length()){
            int start=result.length()-part.length();
            boolean match=true;
            for(int j=0;j<part.length();j++){
                if(result.charAt(start+j)!=part.charAt(j)){
                    match=false;
                    break;
                }
            }
            if(match){
                result.delete(start,result.length());
            }
        }
      }  
      return result.toString();
    }
}