class Solution {
    public int reverseDegree(String s) {

      int sum =0;

      for(int i=0; i<s.length(); i++){
        int degree = 'z'-s.charAt(i)+1;
        int position = i+1;

        sum+= degree*position;
      }
      return sum;
    }
}