package sample;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.webservice.WebService;


//public class DropList extends ListActivity {
//
//	private ArrayList<String> dropPoints;
//	private ArrayList<String> location;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		Intent i = getIntent();
//		Bundle bundle = i.getBundleExtra("bundle");
//		dropPoints = bundle.getStringArrayList("dropPoints");
//		location = bundle.getStringArrayList("location");
//		 
////		ArrayAdapter<ArrayList<String>> adapter = new ArrayAdapter<ArrayList<String>>(this,
////				android.R.layout.simple_list_item_checked, location);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,location);
//		//adapter.setDropDownViewResource(android.R.drawable.btn_radio);
//		
//		setListAdapter(adapter);
//		
//		getListView().setTextFilterEnabled(true);
//		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		
////		TextView text = new TextView(this);
////		text.setText(routeCode);
////
////		text.setMovementMethod(LinkMovementMethod.getInstance());
////		text.setOnClickListener(new OnClickListener() {
////
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////				String uri = String.format("geo:%f,%f", 28.646989, 77.316019);
////				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
////				startActivity(intent);
////				
////			}
////		});
//		//setContentView(text);
//		
//		
//		
//	}
//	String[] city = { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
//			"A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A" };
//	
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		// TODO Auto-generated method stub
//		super.onListItemClick(l, v, position, id);
////		Intent next = new Intent(this,AgenciesWebView.class);
//		Intent next = new Intent(this,TableView.class);
//		//next.putExtra("route", routeCode);
//		startActivity(next);
//		Object o = getListAdapter().getItem(position);
//		getListView().setItemChecked(position, true);
//		String city = o.toString();
////		Toast.makeText(this, city+position, Toast.LENGTH_SHORT).show();
//	}
//
//
//}


public class DropList extends Activity implements OnClickListener, TextWatcher {
	private String URL = "http://119.82.71.87/hdsweb/Service.asmx?";
	private String NameSpace = "http://tempuri.org/";
	private String SoapActionAgencybind = "http://tempuri.org/agencybind";
	private String MethodAgencybind = "agencybind";
	private String SoapActionPublicationBind = "http://tempuri.org/publicationbind";
	private String MethodPublicationBind = "publicationbind";
	private String SoapActionEditionBind = "http://tempuri.org/editionbind";
	private String MethodEditionBind = "editionbind";
	private String SoapActionSupplyBind = "http://tempuri.org/supplybind";
	private String MethodSupplyBind = "supplybind";
	private ArrayList<String> dropPoints;
	private ArrayList<String> location;
	private TableRow row;
	private TextView text;
	private TextView text1;
	private EditText copies;
	private ImageButton Unsoled;


	private EditText comments;
	private EditText signature;
	private Button save;
	private EditText UnsoledwithoutDateWise;
	private int i;
	private EditText edittext;
	private String user;
	private String route;
	private String[] resultString;
	private ArrayList<String> agencySubCode = new ArrayList<String>();
	private ArrayList<String> agencyName = new ArrayList<String>();
	private ArrayList<String> agencyCode = new ArrayList<String>();


	private TableLayout tableLay;
	private LinearLayout Linear;
	private TableLayout lay;
	private TextView text2;
	private Spinner publication;
	private Spinner agency;
	protected String[] publicationResult;
	protected ArrayList<String> publicationName;
	private ArrayList<String> select;
	protected int AgencyId;
	protected ArrayList<String> pubCode;
	protected String agncyCode;
	protected String agncysubcode;
	protected String publicationCode;
	private Spinner edition;
	protected int publicationId;
	protected String[] editionResult;
	protected ArrayList<String> editionName;
	protected ArrayList<String> editionCodeList;
	protected String editionCode;
	protected int editionId;


	protected int copiesId;
	private String[] parents;
	private String[] child;
	private ArrayList<String> listString;
	private String[] agencyNamebind;
	private int m;
	private ArrayList<Integer> count;
	private ArrayList<String> agencyNameList;
	private int r;
	private String[][] finalChild;
	private int l;
	private String[][] temp;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);


		setContentView(R.layout.customlayout1);
		Intent i = getIntent();


		Bundle bundle = i.getBundleExtra("bundle");
		user = bundle.getString("user");
		route = bundle.getString("route");
		dropPoints = bundle.getStringArrayList("dropPoints");


		location = bundle.getStringArrayList("location");
		parents = new String[location.size()];


		String[] childData = new String[10];
		listString = new ArrayList<String>();
		count = new ArrayList<Integer>();
		agencyNameList = new ArrayList<String>();
		//for (int l = 0; l < location.size(); l++) {
			for (int l = 0; l < 5; l++) {


			parents[l] = location.get(l);
			WebService webservice = new WebService(
					"http://119.82.71.87/hdsweb/Service.asmx?",
					"http://tempuri.org/", "http://tempuri.org/agencybind",
					"agencybind");
			String agencies = webservice.callAgencyBind(user, route,
					dropPoints.get(l));
			String newdropPointCode = dropPoints.get(l);
			resultString = agencies.split(":~~:");
			agencyNamebind = new String[resultString.length];
			
			for (m = 0; m < resultString.length; m++) {
				String SubCode = resultString[m].substring(
						resultString[m].indexOf("~") + 1,
						resultString[m].lastIndexOf(")"));
				String Name = resultString[m].substring(0,
						resultString[m].indexOf(']') + 1).toString();
				
				agencyNameList.add(Name);
				String Code = resultString[m].substring(
						resultString[m].lastIndexOf("(") + 1,
						resultString[m].indexOf("~")).toString();
				agencyNamebind[m] = resultString[m].substring(0,
						resultString[m].indexOf(']') + 1).toString();


			}
			count.add(m);
			// Collections.addAll(listString, agencyNamebind);


			// for(int k=0;k<resultString.length;k++){
			// childData[0][l] = resultString[k];}
		}
		//child = new String[location.size()][];
			
////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
//		finalChild = new String[count.size()][8];
//		for (l = 0; l < count.size(); l++) {
//			child = new String[count.size()][8];
//			for (  r = 0; r < count.get(l); r++) {
////				child[l][r] = agencyNameList.get(r).toString();
////			
////				finalChild[l][r]=child[l][r];
//				if(r<count.get(l))
//				{
//					child[l][r] = agencyNameList.get(r).toString();
//				}
//				else
//				{
//					child[l][r] = "";
//				}
//				
//					finalChild[l][r]=child[l][r];
//					
//			}
//			
//		}
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			
			ArrayList<String[]> myList = new ArrayList<String[]>();
			for (l = 0; l < count.size(); l++) {
				child = new String[count.get(l)];
				for (  r = 0; r < count.get(l); r++) {
//					child[l][r] = agencyNameList.get(r).toString();
//				
//					finalChild[l][r]=child[l][r];
//					if(r<count.get(l))
//					{
						//child[0][r] = agencyNameList.get(r).toString();
						child[r]=(agencyNameList.get(r));
					
//					}
//					else
//					{
//						child[l][r] = "Hello".toString();
//					}
					//finalChild = new String{child,child};
						//finalChild[l][r]=child[l][r];
						
				}
				
				myList.add(child);
			}
			
//			for(int q=0;q<myList.size();q++)
//			{
//				temp = myList.get(q);
//			}
//			
//			for (int a = 0; a < myList.size(); a++) {
//				String[][] tempArr = myList.get(a);
//
//				for(int j = 0; j < tempArr.length; j++) {
//				System.
//
//				out.print(tempArr[j]+" ");
//				}
//			}
		String Agencies[][] = new String[myList.size()][];
			//Agencies = myList.toArray(Agencies);
			//////////////////////////////////////////////////////////
			//System.out.println(Agencies);
		
		for (int j = 0; j < Agencies.length; j++) {
			Agencies[j] = (String [])myList.get(j);
		}
			
			
			
//		for(int x=0;x<count.size();x++)
//		{
//			for(int z=0;z<count.get(x);z++)
//			{
//				if(finalChild[x][z].)
//				{
//					finalChild[x][z].replaceAll(null, "");
//				}
//			}
//		}
		ExpandableListView list = (ExpandableListView) findViewById(R.id.expandableListView1);


		lay = (TableLayout) findViewById(R.id.table1);


		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		int orientation = display.getOrientation();
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			LinearLayout listLayout = (LinearLayout) findViewById(R.id.linearLayout2);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					width, height / 3);
			listLayout.setLayoutParams(params);
		}
		list.setAdapter(new MyExpandableListAdapter());
		// list.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_expandable_list_item_1,location));
		// list.setVerticalScrollBarEnabled(false);
//		list.setOnItemClickListener(new OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				agencySubCode = new ArrayList<String>();
//				// agencySubCode.add("Select");
//				agencyName = new ArrayList<String>();
//				// agencyName.add("Select");
//				agencyCode = new ArrayList<String>();
//				// agencyCode.add("Select");
//				Object o = arg0.getItemAtPosition(arg2);
//				String dropPointCode = dropPoints.get(arg2).toString();
//				WebService webservice = new WebService(
//						"http://119.82.71.87/hdsweb/Service.asmx?",
//						"http://tempuri.org/", "http://tempuri.org/agencybind",
//						"agencybind");
//				String agencies = webservice.callAgencyBind(user, route,
//						dropPointCode);
//				resultString = agencies.split(":~~:");
//
//				for (int i = 0; i < resultString.length; i++) {
//					String SubCode = resultString[i].substring(
//							resultString[i].indexOf("~") + 1,
//							resultString[i].indexOf(")"));
//					String Name = resultString[i].substring(0,
//							resultString[i].indexOf(']') + 1).toString();
//					String Code = resultString[i].substring(
//							resultString[i].indexOf("(") + 1,
//							resultString[i].indexOf("~")).toString();
//
//					agencySubCode.add(SubCode);
//
//					agencyName.add(Name);
//					agencyCode.add(Code);
//
//				}
//
//				int k = lay.getChildCount();
//
//				// lay.removeAllViewsInLayout();
//
//				for (int i = 1; i <= k; i++) {
//					View view = lay.getChildAt(1);
//					lay.removeViewInLayout(view);
//				}
//
//				// lay.rem
//
//				getData();
//				// Toast.makeText(DropList.this, "Hello "+o.toString(),
//				// Toast.LENGTH_SHORT).show();
//				// Toast.makeText(DropList.this, ((TextView) arg1).getText(),
//				// Toast.LENGTH_SHORT).show();
//			}
//		});
		ScrollView scrollView = (ScrollView) findViewById(R.id.ScrollView1);
		getHeader();
	}


	private void getData() {
		// TODO Auto-generated method stub


		Linear = (LinearLayout) findViewById(R.id.linearLayout1);


		tableLay = (TableLayout) findViewById(R.id.tableLayout1);


		for (i = 0; i < agencyName.size(); i++) {
			if (i == 0) {
				getHeader();


			}
			row = new TableRow(this);


			TableRow.LayoutParams fieldparams = new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT, 80);


			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_dropdown_item_1line, agencyName);
			publicationName = new ArrayList<String>();
			pubCode = new ArrayList<String>();
			editionName = new ArrayList<String>();
			editionCodeList = new ArrayList<String>();
			agency = new Spinner(this);
			agency.setId(i);


			agency.setAdapter(adapter);
			agency.setLayoutParams(fieldparams);
			agency.setOnTouchListener(new OnTouchListener() {


				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					AgencyId = v.getId();
					return false;
				}
			});
			agency.setOnItemSelectedListener(new OnItemSelectedListener() {


				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					agncyCode = agencyCode.get(arg2);
					agncysubcode = agencySubCode.get(agency
							.getSelectedItemPosition());
					WebService webService = new WebService(URL, NameSpace,
							SoapActionPublicationBind, MethodPublicationBind);
					String resultString = webService.callpublicationbind(
							agncyCode, agncysubcode);
					publicationResult = resultString.split("~");
					pubCode.clear();
					publicationName.clear();
					for (int j = 0; j < publicationResult.length; j++) {
						publicationName.add(publicationResult[j]);
						pubCode.add(publicationResult[i].substring(0,
								publicationResult[j].indexOf("(")));
					}
				}


				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					publicationName.add("Select");
				}
			});


			publication = new Spinner(this);
			publication.setId(i);


			publication.setOnTouchListener(new OnTouchListener() {


				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					publicationId = v.getId();
					if (publicationId == AgencyId) {
						((Spinner) v).setAdapter(setIteminPublication());
					}
					return false;
				}
			});
			publication.setLayoutParams(fieldparams);
			publication.setOnItemSelectedListener(new OnItemSelectedListener() {


				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					editionName.clear();
					editionCodeList.clear();
					publicationCode = pubCode.get(arg2);
					WebService webService = new WebService(URL, NameSpace,
							SoapActionEditionBind, MethodEditionBind);
					String resultString = webService.calleditionbind(agncyCode,
							agncysubcode, publicationCode);
					editionResult = resultString.split("~");
					for (int j = 0; j < editionResult.length; j++) {
						editionName.add(editionResult[j]);
						editionCodeList.add(editionResult[j].substring(0,
								editionResult[i].indexOf("(")));


					}
					setIteminEdition();
				}


				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub


				}
			});


			edition = new Spinner(this);


			edition.setId(i);


			edition.setOnTouchListener(new OnTouchListener() {


				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					editionId = v.getId();


					if (editionId == publicationId && editionId == AgencyId) {
						((Spinner) v).setAdapter(setIteminEdition());
					}
					return false;
				}
			});
			edition.setLayoutParams(fieldparams);
			edition.setOnItemSelectedListener(new OnItemSelectedListener() {


				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					// publicationCode = pubCode.get(arg2);
					editionCode = editionCodeList.get(arg2);
					int copiesId = editionId;
					WebService webService = new WebService(URL, NameSpace,
							SoapActionSupplyBind, MethodSupplyBind);
					// String resultString =
					// webService.callsupplybind(agncyCode,
					// agncysubcode,publicationCode,editionCode);
					String resultString = webService.callsupplybind("T0002",
							"0001", "P06", "139");
					String[] copiesString = resultString.split(":~:");


					if (copiesId == AgencyId && copiesId == publicationId
							&& copiesId == editionId) {
						copies = (EditText) findViewById(300 + copiesId);
						copies.setText(copiesString[0]);
					}


				}


				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub


				}
			});


			copies = new EditText(this);
			copies.setInputType(InputType.TYPE_CLASS_NUMBER);
			copies.setLines(1);
			copies.setTag("No of Copies");
			// copies.setMaxWidth(50);
			copies.setId(300 + i);
			BitmapDrawable calendar = (BitmapDrawable) getResources()
					.getDrawable(R.drawable.calendar);
			Bitmap calndr = calendar.getBitmap();


			Unsoled = new ImageButton(this);
			// fieldparams.setMargins(0, 25, 0, 0);
			Unsoled.setLayoutParams(fieldparams);
			Unsoled.setImageDrawable(getResources().getDrawable(
					R.drawable.calendar));


			Unsoled.setOnClickListener(this);
			Unsoled.setId(100 + i);
			UnsoledwithoutDateWise = new EditText(this);
			// UnsoledwithoutDateWise.setMaxWidth(100);
			UnsoledwithoutDateWise.setLines(1);
			UnsoledwithoutDateWise.setId(i);
			comments = new EditText(this);


			comments.setLines(1);
			// comments.setMaxWidth(200);
			comments.setId(200 + i);
			if (i == 0) {
				signature = new EditText(this);
				signature.setMaxWidth(200);
				signature.setLines(1);
				signature.addTextChangedListener(this);
				signature.setId(i);
			} else {
				signature = new EditText(this);
				signature.setMaxWidth(200);
				signature.setLines(1);
				signature.setEnabled(false);
				signature.setId(800 + i);
			}
			save = new Button(this);
			save.setText("Save");
			save.setId(i);
			row.addView(agency);
			row.addView(publication);
			row.addView(edition);
			row.addView(copies);
			row.addView(Unsoled);
			row.addView(UnsoledwithoutDateWise);
			row.addView(comments);
			// row.addView(signature);
			row.addView(save);
			row.setTag("row");
			lay.addView(row);
			EditText signature1 = (EditText) findViewById(R.id.editText1);
			int i = lay.getChildCount();
		}
	}


	protected ArrayAdapter<String> setIteminEdition() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> editionAdapter = new ArrayAdapter<String>(
				DropList.this, android.R.layout.simple_dropdown_item_1line,
				editionName);
		return editionAdapter;
	}


	protected ArrayAdapter<String> setIteminPublication() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> publicationAdaper = new ArrayAdapter<String>(
				DropList.this, android.R.layout.simple_dropdown_item_1line,
				publicationName);
		return publicationAdaper;
	}


	private void getHeader() {
		// TODO Auto-generated method stub
		TableRow row = (TableRow) findViewById(R.id.TableRow01);
		TextView row1 = (TextView) findViewById(R.id.TextView1);
		row1.setText("  Agency  ");


		TextView row2 = (TextView) findViewById(R.id.TextView2);


		row2.setText("  Publication  ");
		TextView row3 = (TextView) findViewById(R.id.TextView3);


		row3.setText("  Edition  ");
		row3.setInputType(InputType.TYPE_CLASS_NUMBER);
		TextView row4 = (TextView) findViewById(R.id.TextView4);
		row4.setText(" Copies  ");
		TextView row5 = (TextView) findViewById(R.id.TextView5);
		row5.setText(" DateWise ");
		TextView row6 = (TextView) findViewById(R.id.TextView6);
		row6.setText("  Without DateWise  ");
		TextView row7 = (TextView) findViewById(R.id.TextView7);
		row7.setText("  Comments  ");
		TextView row8 = (TextView) findViewById(R.id.textView08);
		row8.setText("  Action  ");


	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent nextActivity = new Intent(this, PopupScreen.class);
		startActivity(nextActivity);
	}


	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub


	}


	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub


	}


	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		for (int k = 1; k < i; k++) {
			edittext = (EditText) findViewById(800 + k);
			edittext.setText(s);
		}


	}


	public class MyExpandableListAdapter extends BaseExpandableListAdapter {
		// Sample data set. children[i] contains the children (String[]) for
		// groups[i].
		private String[] groups = parents;


		private String[][] children = temp;
//		{ 
//		{ "abc", "xyz", "ash", "anu" },
//		{ "SSE", "TJ", "PM", "SE" },
//		{ "Male", "Female" },
//		{ "yyyyyy", "xxxxx" }
//		};


//		public MyExpandableListAdapter() {
//
//			groups = parents;
//			child = new String[location.size()][];
//			for (int l = 0; l < count.size(); l++) {
//				child = new String[1][count.get(l)];
//				for (int r = 0; r < count.get(l); r++) {
//					child[0][r] = agencyNameList.get(r).toString();
//				}
//			}
//		}


		public Object getChild(int groupPosition, int childPosition) {
			return children[groupPosition][childPosition];
		}


		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}


		public int getChildrenCount(int groupPosition) {
			return children[groupPosition].length;
		}


		public TextView getGenericView() {
			// Layout parameters for the ExpandableListView
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, 64);
			TextView textView = new TextView(DropList.this);
			textView.setLayoutParams(lp);
			// Center the text vertically
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView.setPadding(56, 0, 0, 0);
			return textView;
		}


		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			TextView textView = getGenericView();
			textView.setText(getChild(groupPosition, childPosition).toString());
			return textView;
		}


		public Object getGroup(int groupPosition) {
			return groups[groupPosition];
		}


		public int getGroupCount() {
			return groups.length;
		}


		public long getGroupId(int groupPosition) {
			return groupPosition;
		}


		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			TextView textView = getGenericView();
			textView.setText(getGroup(groupPosition).toString());
			return textView;
		}


		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}


		public boolean hasStableIds() {
			return true;
		}
	}
}