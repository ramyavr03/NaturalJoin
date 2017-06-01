
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.HashMap;

	public class NaturalJoin {
		
		
		static  int[] append(int[] arr, int element) {
		    final int N = arr.length;
		    arr = Arrays.copyOf(arr, N + 1);
		    arr[N] = element;
		    return arr;
		}
		
		public static ArrayList<int[]> JoinIndex(ArrayList<int[]> newS, ArrayList<int[]> newR) 
		{
			HashMap<Integer, int[]> map1= new HashMap<Integer, int[]>();
		    ArrayList<int []> bufferOutput = new ArrayList<int[]>(3); //Output buffer of size 3
	        ArrayList<int[]> diskOutput = new ArrayList<int[]>(15);  //Output disk of size 15

		     //  Storing the tuples of Relation R into hashmap for finding the Natural Join 
		      for(int[] i : newR)
		      {  /*assigning the secondary index on the Y attribute and putting  
		         secondary index as the key in the hashmap for each of the tuples of the block */
		    	    map1.put(i[2],i); 
		            
		      }
		      
		      
		      for(int [] k : newS)
		      {
		    	  /*checking if the y attribute value(Primary Key) of S matches with secondary index of R which is in the map as key ,
		    	  if yes then we do a natural join*/

		    	  if(map1.containsKey(k[0]))		    	  
		    	  {
		    		  int [] Join = new int[10];
		    			  Join = map1.get(k[0]);
		    			  Join = append(Join,k[1]);
		    			  Join = append(Join,k[2]);
		    		  if(bufferOutput.size()<3)
	     	          {
	     	             bufferOutput.add(Join);
	     	              System.out.println("Buffer contents after writing to Outputbuffer:");

	     	              for(int[] m:bufferOutput)
	     	              {
	     	                  System.out.println(Arrays.toString(m));
	     	              }
	     	           }
	     	             else
	     	            {
	     	                System.out.println("Buffer is full writing the buffer contents to disk:");
	     	                diskOutput.addAll(bufferOutput);
	     	                for(int[] n:diskOutput)
	     	                {
	     	                	System.out.println(Arrays.toString(n));

	     	                }
	     	                bufferOutput = new ArrayList<int[]>(3); //clear the buffer after writing it to the disk by reintializing it
	     	                bufferOutput.add(Join);//after clearing the full buffer we add the next common elements in queue to be written into the buffer
	     	            }	    		  
		      }
		      
		}
		      	      
	 diskOutput.addAll(bufferOutput);
	return diskOutput;
		}
		public static void main(String[] args) 
		{   //No of Blocks=1
			// Block values of Relation R in disk     			
	        
			int[][] R={{2,4,1},{3,9,5},{6,2,5},{2,5,9},{9,1,2},{13,2,4},{7,5,8},{1,5,6}};    
			//  Block values of Relation S in disk
			//Y values of the tuples are sorted in the Relation S
		   int[][] S= {{1,3,2},{2,4,6},{2,6,0},{3,2,4},{4,8,6},{5,2,3}};
		   
		
		   ArrayList<int[]> newS= new ArrayList<int[]>(5);
		   ArrayList<int[]> newR= new ArrayList<int[]>(5);
		               
	              //Loading block values of Relation R and S into Memory
		               for(int i=0;i<S.length;i++)
		               {
		                  newS.add(S[i]);
		               } 
		               for(int i=0;i<R.length;i++)
		               {
		                                  
		                  newR.add(R[i]);
		               }  
		               //Function call to find the Natural Join
		               ArrayList<int[]> result= JoinIndex(newS, newR);
		               int[][] NaturalJoin = new int[result.size()][];
		               for (int i = 0; i < result.size(); i++) {
		                   int[] row  =result.get(i);
		                   NaturalJoin[i] = row;
		               }
			           System.out.println("The Natural Join of  R and S");
		               System.out.println(Arrays.deepToString(NaturalJoin));



		    
		   }
	}


