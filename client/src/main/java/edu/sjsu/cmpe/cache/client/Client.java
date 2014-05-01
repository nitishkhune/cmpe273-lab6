package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
        String [] value = {"0","Ferrari 250 GTO", "Ferrari 250 Testa Rossa", "Jaguar XJ13", "Mercedes-Benz SLR McLaren", "Ferrari 330 P4", "Maybach Exelero", "Rolls-Royce Hyperion", "Lamborghini Veneno", "Zenvo ST1", "Audi Le Mans Concept" ," McLaren X-1 Concept" , "Koenigsegg CCXR Trevita"};
        List<DistributedCacheService> server = new ArrayList<DistributedCacheService>();
        server.add(new DistributedCacheService("http://localhost:3000"));
        server.add(new DistributedCacheService("http://localhost:3001"));
        server.add(new DistributedCacheService("http://localhost:3002"));
        
        System.out.println(" ----------------------Putting values to server------------------");
        for(int putkey=1; putkey<=12; putkey++)	{
        	int bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(putkey)), server.size());
        	server.get(bucket).put(putkey, value[putkey]);
        	System.out.println("The key value pair " + putkey +"-" + value[putkey]+ " is assigned to server " + bucket);
        }
        System.out.println(" ----------------------Getting values from server------------------");
        for(int getkey=1; getkey<=12; getkey++)	{
        	int bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(getkey)), server.size());
        	System.out.println("The key value pair " + getkey +"-" + server.get(bucket).get(getkey)+ " is received to server " + bucket);
        	
        }
        System.out.println(" ------------------------------Terminated---------------------------");
        
    }

}
