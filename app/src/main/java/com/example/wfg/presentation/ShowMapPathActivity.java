package com.example.wfg.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wfg.presentation.HomePage;

import com.example.wfg.presentation.R;

import java.util.ArrayList;



public class ShowMapPathActivity extends AppCompatActivity {
   public static Context ctxmapPath;
    String srcl,desl;
    Button backbutton1;
    public  static  int num=0;
    public  static  int numDest=0;

    public static final int NO_PARENT = -1;
    public  static View ViewFirstToTwo,ViewFirstToThird,ViewThreeToFour,ViewTwoToFourth,ViewTwoToThree,
            ViewTwoToFive,ViewFourToFive;

    public static int startVertexs=0;
    public  static  String var;

    public  static ArrayList<String> pathArrays=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map_path_layout);
        ctxmapPath=this;
        srcl=getIntent().getStringExtra("srcl");
        desl=getIntent().getStringExtra("desl");

        System.out.println("Source**:-"+srcl);
        System.out.println("Destination**:-"+desl);

        num=0;
        initIds();


        funForSource();
        funForDest();
        adjacencyMatrice(num);



        backbutton1=(Button) findViewById(R.id.backbutton1);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(ctxmapPath, ActivityQRCodeLayout.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);
            }
        });


    }

    public void  funForSource()
    {
        if(srcl.equals("201")) {
        num=0;
        Log.e("num:",""+num);
        } else if(srcl.equals("202")) {
        num=1;
            Log.e("num:",""+num);
        } else if(srcl.equals("203")) {
        num=2;
            Log.e("num:",""+num);
        } else if(srcl.equals("204")) {
        num=3;
            Log.e("num:",""+num);
        } else if(srcl.equals("205")){
        num=4;
            Log.e("num:",""+num);
        }
    }

    public void funForDest() {
        {
            if (desl.equals("201")) {
                numDest = 0;
                Log.e("num:",""+numDest);
            } else if (desl.equals("202")) {
                numDest = 1;
                Log.e("num:",""+numDest);
            } else if (desl.equals("203")) {
                numDest = 2;
                Log.e("num:",""+numDest);
            } else if (desl.equals("204")) {
                numDest = 3;
                Log.e("num:",""+numDest);
            } else if (desl.equals("205")) {
                numDest = 4;
                Log.e("num:",""+numDest);
            }
        }
    }

    public void initIds(){

        ViewFirstToTwo=(View)findViewById(R.id.ViewFirstToTwo);
        ViewFirstToThird=(View)findViewById(R.id.ViewFirstToThird);
        ViewThreeToFour=(View)findViewById(R.id.ViewThreeToFour);
        ViewTwoToFourth=(View)findViewById(R.id.ViewTwoToFourth);
        ViewTwoToThree=(View)findViewById(R.id.ViewTwoToThree);
        ViewTwoToFive=(View)findViewById(R.id.ViewTwoToFive);
        ViewFourToFive=(View)findViewById(R.id.ViewFourToFive);



    }



    public void adjacencyMatrice(int num)
    {
        int[][] adjacencyMatrix = {
                { 0, 6, 1, 0, 0},
                { 6, 0, 2, 2, 5},
                { 1, 2, 0, 1, 0},
                { 0, 2, 1, 0, 5},
                { 0, 5, 0, 5, 0}
        };

        dijkstra(adjacencyMatrix, num);



    }



    private static void dijkstra(int[][] adjacencyMatrix, int startVertex)
    {
        int nVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[nVertices];

        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices;
             vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[nVertices];

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++)
        {

            // Pick the minimum distance vertex from the set of vertices not yet
            // processed. nearestVertex is  always equal to startNode in first iteration.

            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                if (!added[vertexIndex] &&
                        shortestDistances[vertexIndex] <
                                shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as processed
            added[nearestVertex] = true;

            // Update dist value of the adjacent vertices of the picked vertex.
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++)
            {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0 &&
                        ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }

        printSolution(startVertex, shortestDistances, parents);
    }

    // A utility function to print the constructed distances  array and shortest paths
    private static void printSolution(int startVertex, int[] distances, int[] parents)
    {
        startVertexs=startVertex;
        int nVertices = distances.length;
        System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");

                Log.e("Path:-",""+startVertex+"to"+vertexIndex+" :"+distances[vertexIndex]);
               // printPath(vertexIndex, parents);

               if(startVertex==num)
               {
                   if(startVertex==0)
                   {
                       Log.e("Zero:","ZeroVerttex");
                       printPath(vertexIndex, parents);
                       firstFun(vertexIndex,distances[vertexIndex],pathArrays);
                   }else if(startVertex==1)
                   {
                       Log.e("First:","FirstVerttex");
                       printPath(vertexIndex, parents);
                       secondFun(vertexIndex,distances[vertexIndex],pathArrays);
                   }
                   else if(startVertex==2)
                   {
                       Log.e("Second:","SecondVerttex");
                       printPath(vertexIndex, parents);
                       thirdFun(vertexIndex,distances[vertexIndex],pathArrays);
                   }
                   else if(startVertex==3)
                   {
                       Log.e("Third:","ThirdVerttex");
                       printPath(vertexIndex, parents);
                       fourthFun(vertexIndex,distances[vertexIndex],pathArrays);
                   }
                   else if(startVertex==4)
                   {
                       Log.e("Fourth:","FourthVerttex");
                       printPath(vertexIndex, parents);
                       fifthFun(vertexIndex,distances[vertexIndex],pathArrays);
                   }
               }
            }
        }
    }
    // Function to print shortest path from source to currentVertex using parents array
    private static void printPath(int currentVertex, int[] parents)
    {
        pathArrays.clear();
        var="";
        // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        pathArrays.add(String.valueOf(currentVertex));
        System.out.print(currentVertex + " ");
        Log.e("pathess:",""+currentVertex);




    }

    public static  void firstFun(int dest,int dist,ArrayList<String> pathArray){

        Log.e("pathessArray:",""+pathArray);

        if(dest == numDest && pathArray.contains("0")&&pathArray.contains("2")&&
                pathArray.contains("3")&&pathArray.contains("4"))

        {
            ViewFirstToThird.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            ViewThreeToFour.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            ViewFourToFive.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            Log.e("dist;;",""+dist);
        }
         else if(dest == numDest && pathArray.contains("0") &&pathArray.contains("2") &&pathArray.contains("3"))

        {
            ViewFirstToThird.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            ViewThreeToFour.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            Log.e("dist;;",""+dist);
        }
          else if (dest == numDest && pathArray.contains("0")&&pathArray.contains("2")
                    &&pathArray.contains("1"))
            {
                ViewFirstToThird.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
                ViewTwoToThree.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
                Log.e("dist;;",""+dist);
            }
            else if(dest == numDest && pathArray.contains("0")
                    &&pathArray.contains("2"))
            {
                ViewFirstToThird.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
                Log.e("dist;;",""+dist);
            }


        //}

    }

    public static  void secondFun(int dest,int dist,ArrayList<String> pathArray){
        Log.e("pathessArray:",""+pathArray);

            if (dest == numDest && pathArray.contains("1")&&pathArray.contains("2")
                    &&pathArray.contains("0"))
            {
                ViewFirstToThird.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
                ViewTwoToThree.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            }else if(dest == numDest && pathArray.contains("1")&&pathArray.contains("2"))
            {
                ViewTwoToThree.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            }else if(dest == numDest && pathArray.contains("1")&&pathArray.contains("3"))
            {
                ViewTwoToFourth.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            }
            else if(dest == numDest && pathArray.contains("1")&&pathArray.contains("4"))
            {
                ViewTwoToFive.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            }


    }

    public static  void thirdFun(int dest,int dist,ArrayList<String> pathArray){
        Log.e("pathessArray:",""+pathArray);

         if(dest == numDest && pathArray.contains("2")
                &&pathArray.contains("3")&&pathArray.contains("4"))
        {
            ViewThreeToFour.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            ViewFourToFive.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }
        else if (dest == numDest && pathArray.contains("2")
                &&pathArray.contains("0"))
        {
            ViewFirstToThird.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }else if(dest == numDest && pathArray.contains("2")
                &&pathArray.contains("1"))
        {
            ViewTwoToThree.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }else if(dest == numDest && pathArray.contains("2")
                &&pathArray.contains("3"))
        {
            ViewThreeToFour.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }



    }

    public static  void fourthFun(int dest,int dist,ArrayList<String> pathArray){
        Log.e("pathessArray:",""+pathArray);

        if (dest == numDest && pathArray.contains("3")&&pathArray.contains("2")
                &&pathArray.contains("0"))
        {
            ViewFirstToThird.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            ViewThreeToFour.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }else if(dest == numDest && pathArray.contains("3")&&pathArray.contains("1"))
        {
            ViewTwoToFourth.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }else if(dest == numDest && pathArray.contains("3")&&pathArray.contains("2"))
        {
            ViewThreeToFour.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }
        else if(dest == numDest && pathArray.contains("3")&&pathArray.contains("4"))
        {
            ViewFourToFive.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }


    }

    public static  void fifthFun(int dest,int dist,ArrayList<String> pathArray){

        Log.e("pathessArray:",""+pathArray);
        if (dest == numDest && pathArray.contains("4")&&pathArray.contains("3")
                &&pathArray.contains("2")&&pathArray.contains("0"))
        {
            ViewFirstToThird.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            ViewThreeToFour.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            ViewFourToFive.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }
        else if(dest == numDest &&  pathArray.contains("4")&&
                pathArray.contains("3") &&pathArray.contains("2"))
        {
            ViewThreeToFour.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
            ViewFourToFive.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }
        else if(dest == numDest &&  pathArray.contains("4")&&pathArray.contains("1"))
        {
            ViewTwoToFive.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }

        else if(dest == numDest &&  pathArray.contains("4")&&pathArray.contains("3"))
        {
            ViewFourToFive.setBackgroundColor(ContextCompat.getColor(ctxmapPath, R.color.colorAccent));
        }


    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}



