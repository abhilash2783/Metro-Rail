package com.metroinfo.gni;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

public class DatabaseHandler extends SQLiteOpenHelper{

	
	
	private static String DB_PATH = "/data/data/com.coign.metro/databases/";
	 
    private static String DB_NAME = "metroinfo.sqlite";
    private static String TABLENAME = "stations";
    private SQLiteDatabase myDataBase;   
    int trainid ;
    private final Context myContext;
    private SQLiteDatabase db;
    Cursor c1,c2;
    String a[];
	public DatabaseHandler(Context context) {
		super(context, DB_NAME, null, 1);
        this.myContext = context;
	}
	public int onCreateDataBase() throws IOException
	{
		boolean dbExist=checkDatabase();
		if(dbExist){
    		 return 0;
    	}else{
    		 System.out.println("onCreateDataBase method execution starts");
    	
        	this.getReadableDatabase();
 
        	
 
    			copyDataBase();
    			return 1;	
    		
    	}
	}

	private boolean checkDatabase() {
		// TODO Auto-generated method stub
		SQLiteDatabase checkDB=null;
		try
		{

			String myPath = DB_PATH + DB_NAME;
			
			checkDB=SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		if(checkDB != null){
			 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true: false ;
		
	
	}
	
	private void copyDataBase() throws IOException{
		
	InputStream myInput=myContext.getAssets().open(DB_NAME);
	
	
	// Path to the just created empty db
	String outFileName = DB_PATH + DB_NAME;

	//Open the empty db as the output stream
	OutputStream myOutput = new FileOutputStream(outFileName);

	//transfer bytes from the inputfile to the outputfile
	byte[] buffer = new byte[1024];
	int length;
	while ((length = myInput.read(buffer))>0){
		myOutput.write(buffer, 0, length);
		
	
	}

	//Close the streams
	myOutput.flush();
	myOutput.close();
	myInput.close();
	
	}
	
    public void openDataBase() throws SQLException{
    	 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<String> selectStations(SQLiteDatabase db,String sr) {
		// TODO Auto-generated method stub
			
		// TODO Auto-generated method stub
	 String P_query123="SELECT Station FROM "+sr;
	 			 
	 ArrayList<String> list=new ArrayList<String>();
	 
	 Cursor c=db.rawQuery(P_query123, null); 
	 
	
		if(c.moveToFirst())  
		{  
			do{ 	   
				list.add(c.getString(c.getColumnIndex("Station")));           
			}while(c.moveToNext());  
		}
			
		  
	 
	 return list;
	
	}
	public ArrayList<String> selectSchedules(SQLiteDatabase db,String route,String sta, String i, String j) {
		// TODO Auto-generated method stub
		
		String P_query123="SELECT * FROM "+route;
		
 ArrayList<String> list=new ArrayList<String>();
 
 Cursor c=db.rawQuery(P_query123, null);
		
		for(int i3=1;i3<c.getColumnCount();i3++)
		{
			String sindexvalues=c.getColumnName(i3);
			System.out.println(sindexvalues);
			list.add(sindexvalues);
		}
		

		 return list;
	}
public String selectStationsBaseTime(SQLiteDatabase db,
			String tid,String tname,String fromtime,String totime,String stationname) {
		// TODO Auto-generated method stub
			//System.out.println("Database values :"+tid);
			//System.out.println("Database values :"+tname);
		// TODO Auto-generated method stub
	 String P_query234="SELECT "+tid+" FROM "+tname+" where Station='"+stationname+"' and "+tid+" between '"+fromtime+"' and '"+totime+"'";
	 			 
	 String list=null;
	 
	 Cursor cval=db.rawQuery(P_query234, null);  
	 
		//System.out.println("nbdkjabjashda  cursor ajs"+cval.getColumnIndex(tid));
		
		if(cval.moveToFirst())   
		{  
			
			do{ 	       
				String  list2=cval.getString(cval.getColumnIndex(tid));           
				System.out.println("#$@#@#@#"+list2);
			list=list2;
			
			}while(cval.moveToNext());  
		}
			   
		
		  
	 String result=tid+"~"+list;
	 System.out.println("#$@#@#@#"+result);
	 return result;
	
	}
public ArrayList<String> selectStations(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	String P_query123="SELECT Station FROM FalaknumaToLingampally " +
			"union " +
			"SELECT Station FROM HyderabadToLingampally";
	 
	 ArrayList<String> list=new ArrayList<String>();
	 
	 Cursor c=db.rawQuery(P_query123, null); 
	 
		
		if(c.moveToFirst())  
		{  
			do{  				   
				list.add(c.getString(c.getColumnIndex("Station")));           
			}while(c.moveToNext());  
		}
			
	 
	 return list;
	
	}
public ArrayList<String> selectStationsForretriving(SQLiteDatabase db,
		String n1, String n2, String n3, String n4) {
	// TODO Auto-generated method stub
	ArrayList<String> ardata=new ArrayList<String>();
	 
	String[] tablearray=new String[]{"HyderabadToLingampally","FalaknumaToLingampally",
			"LingampallyToHyderabad","LingampallyToFalaknuma","FalaknumaToHyderabad","HyderabadToFalaknuma"};
	System.out.println("valllll"+tablearray.length); 
	 ArrayList<String> list1=new ArrayList<String>();
	 ArrayList<String> list12=new ArrayList<String>();
	 ArrayList<String> values=new ArrayList<String>();
	 ArrayList<String> svalues=new ArrayList<String>();
	 ArrayList<String> stationvalues=new ArrayList<String>();
	 ArrayList<String> resultsfinal=new ArrayList<String>();
	 ArrayList<String> resultsfinal1=new ArrayList<String>();
	 
	 ArrayList<String> forprocess=new ArrayList<String>();
	   a=new String[tablearray.length];
	   
	   String b[] =new String[4];
	   String d[];
	   String resulttablename=null;
	  int src;
	   String srcname,destname; 
		int k=0;
	for(int i=0;i<tablearray.length;i++)
	{
		
		c1=db.rawQuery("SELECT * FROM "+tablearray[i]+" WHERE Station='"+n1+"' or Station='"+n2+"' " , null);
		
		   int ii=c1.getCount();
		   	
		   if(ii==2)  
		   {
			
		
		   	  b[k]=tablearray[i];
				k++;
			  
		   }else
		   {
			   System.out.println("@@@not fount@@@");
			   
		   }
			
		 
		
	
	}
	

	
	for(int i=0;i<b.length;i++)
	{
		
		if(b[i]!=null)
		{
		
			forprocess.add(b[i]);
			
			
		}
	}
	for(int i=0;i<forprocess.size();i++)
	{
		if(forprocess.size()==2)
		{
			Cursor c2=db.rawQuery("SELECT Station,Sid FROM "+forprocess.get(i)+" WHERE Station='"+n1+"' or Station='"+n2+"' " , null);
			
			if(c2.moveToFirst())  
			{  
				do{  				   
								
					
					stationvalues.add(c2.getString(c2.getColumnIndex("Station"))); 
					svalues.add(c2.getString(c2.getColumnIndex("Sid")));  
				
					if(svalues.size()==2)
					{
				if(Integer.parseInt(svalues.get(0))<Integer.parseInt(svalues.get(1)))
					{
						if(n1.equals(stationvalues.get(0)))
						{
							
							resulttablename=b[i];
							
							resultsfinal=getValues(db,resulttablename,n1,n2,n3,n4);
							break;
						}
					}
				svalues.clear();
				stationvalues.clear();
					}
					
				}while(c2.moveToNext());  
			}
			
		}else
		{
				Cursor c2=db.rawQuery("SELECT Station,Sid FROM "+forprocess.get(i)+" WHERE Station='"+n1+"' or Station='"+n2+"' " , null);
			
				if(c2.moveToFirst())  
				{  
				do{  		
					stationvalues.add(c2.getString(c2.getColumnIndex("Station"))); 
					svalues.add(c2.getString(c2.getColumnIndex("Sid")));
					
					if(svalues.size()==2)
					{
						if(Integer.parseInt(svalues.get(0))<Integer.parseInt(svalues.get(1)))
					{
						if(n1.equals(stationvalues.get(0)))
						{
							
							ArrayList<String> temp1=new ArrayList<String>();
							temp1.add(b[i]);
							for(int j=0;j<temp1.size();j++)
							{
								
								resultsfinal.addAll(getValues(db,temp1.get(j),n1,n2,n3,n4));
							}
							
						}  
					}
				svalues.clear();
				stationvalues.clear();
					
					
					}
					
					
					
					
				
				  }while(c2.moveToNext()); 
		         }
	   }
	}
	return resultsfinal;  
}
		
		



private ArrayList<String> getValues(SQLiteDatabase db,String resulttablename,String n1,String n2,String n3,String n4) {
	// TODO Auto-generated method stub
	 ArrayList<String> values=new ArrayList<String>();
	 ArrayList<String> list1=new ArrayList<String>();
	 ArrayList<String> list12=new ArrayList<String>();
	
	
	
	String P_query123="SELECT * FROM "+resulttablename;
	 
    Cursor c22=db.rawQuery(P_query123, null);
			
			for(int i3=1;i3<c22.getColumnCount();i3++)
			{
				String sindexvalues=c22.getColumnName(i3);
				
				list1.add(sindexvalues);
			}  
			//SELECT Sid FROM FalaknumaToLingampally where Station='Falaknuma' or  Station='Khacheguda'
			
	String P_query1234="SELECT Sid FROM "+resulttablename+" where Station='"+n1+"' or Station='"+n2+"'";
	Cursor c222=db.rawQuery(P_query1234, null);
	int iid=c222.getColumnIndex("Sid");
	
	if(c222.moveToFirst())  
	{  
		do{  				   
			list12.add(c222.getString(iid));           
		}while(c222.moveToNext()); 
	}
	 String list=null;
	 String listStations=null;
	for(int g=0;g<list1.size();g++)
	{//SELECT Station,FL1 FROM FalaknumaToLingampally where Sid between 1 and 9 and FL1 between '0500' and '0511'
		Cursor resultccc=db.rawQuery("SELECT Station,"+list1.get(g)+" FROM "+resulttablename+" where Sid between '"+list12.get(0)+"' and '"+list12.get(1)+"' and "+list1.get(g)+" between '"+n3+"' and '"+n4+"'",null);;
	
		//System.out.println("nbdkjabjashda  cursor ajs"+resultccc.getColumnIndex(list1.get(g)));
		
		if(resultccc.moveToFirst())   
		{  
			
			do{ 	  
				listStations=resultccc.getString(resultccc.getColumnIndex("Station")); 
				String  list2=resultccc.getString(resultccc.getColumnIndex(list1.get(g)));   
				String lis1=list2.substring(0, 2);
				String lis2=list2.substring(2, 4);
				String lis3=lis1+":"+lis2;
				
				String fresultwithconcatination=listStations+"~"+lis3;  
				
				values.add(list1.get(g));
				
				values.add(fresultwithconcatination); 
			
				
				
			
			
			
			}while(resultccc.moveToNext());  
		}
	
		

	}
	
	return values;
}





public ArrayList<String> selectFares(SQLiteDatabase db) {
	// TODO Auto-generated method stub

	
	String P_query123="SELECT * FROM Trainfares";
	
	 ArrayList<String> list=new ArrayList<String>();
	 
	 Cursor c=db.rawQuery(P_query123, null);
			
		int j1=c.getColumnIndex("KM");
		int j2=c.getColumnIndex("Fare");
			
	if(c.moveToFirst())
	{
	do{
		
		String kms=c.getString(j1);
		int fr=c.getInt(j2);
	String fr1=Integer.toString(fr);
	String fr11=kms.substring(0,2);
	String fr22=kms.substring(2, 4);
	String fr33=fr11+"-"+fr22+"Km";
		list.add(fr33);
		list.add(fr1);
	}
	while(c.moveToNext());
	}
	
	
	return list;
}
}
