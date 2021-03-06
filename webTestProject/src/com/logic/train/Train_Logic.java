package com.logic.train;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.train.dao.TrainDAO;
import com.train.vo.ChargeListVO;
import com.train.vo.CityVO;
import com.train.vo.CodeSearchVO;
import com.train.vo.SeatVO;
import com.train.vo.TableCheckListVO;
import com.train.vo.TimeVO;
import com.train.vo.TrainBookVO;
import com.train.vo.VehicleVO;

public class Train_Logic {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	ArrayList<CityVO> cList = null;
	ArrayList<VehicleVO> vList = null;
	ArrayList<TimeVO> dList = null;
	ArrayList<SeatVO> sList = null;
	TrainDAO tDao  = null;
	
	
	public ArrayList<TimeVO> depart_check(String select_date,String depart_station,String last_station){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today=formatter.format(date);
		tDao = new TrainDAO();
		
		if(today.equals(select_date)){
			return tDao.train_todayDepartTime(depart_station,last_station);
		}
		else{
			return tDao.train_othersDepartTime(depart_station,last_station);
		}
	}
	
	
	/*public ArrayList<DadptVO> arraval_check(String select_date,String depart_station,String last_station){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today=formatter.format(date);
		tDao = new TrainDAO();
		
		if(today.equals(select_date)){
			return tDao.arrivalTodayTime(depart_station, last_station);
		}
		else{
			return tDao.arrivalOthersTime(depart_station, last_station);
		}
	}*/
	
	public ArrayList<CityVO> getCityList(){
		tDao = new TrainDAO();
		cList = new ArrayList<CityVO>();
		cList = tDao.train_citySearch();

		return cList;
	}
	
	public ArrayList<VehicleVO> getVehicleList(){
		tDao = new TrainDAO();
		vList = new ArrayList<VehicleVO>();
		vList = tDao.train_vehicleSearch();

		return vList;
	}
	
	public ArrayList<TimeVO> getDepartTimeList(){
		tDao = new TrainDAO();
		dList = new ArrayList<TimeVO>();
		dList = tDao.train_dTimeSearch();

		return dList;
	}
	
	public ArrayList<SeatVO> getStandardSeatList(String vehicle_kinds,String start_city, String arrival_city, String time_time,String dp_date){
		tDao = new TrainDAO();
		sList = new ArrayList<SeatVO>();
		sList = tDao.train_seatStandardSearch(vehicle_kinds ,start_city, arrival_city, time_time,dp_date); 

		return sList;
	}
	
	
	public ArrayList<SeatVO> getVipSeatList(String vehicle_kinds,String start_city, String arrival_city, String time_time,String dp_date){
		tDao = new TrainDAO();
		sList = new ArrayList<SeatVO>();
		sList = tDao.train_seatVipSearch(vehicle_kinds ,start_city, arrival_city, time_time,dp_date);
		
		return sList;
	}
	
	
	
	public String todayDate(){
		Date date = new Date();
		String today = formatter.format(date);
		return today;
	}
	
	
	public String enddayDate(){
		Calendar cal = Calendar.getInstance ( );						
		cal.add( Calendar.MONTH, 1);
		Date tomorrow = cal.getTime();
		String endday = formatter.format(tomorrow);
		return endday;
	}
	
	
	//어른 일반실 요금
	public int standardCharge(String tType, String dpStation,String avStation){
		tDao = new TrainDAO();
		int charge = 0;
		
		if("무궁화호".equals(tType)){
			charge = tDao.sharonTrain_charge(dpStation,avStation);
		}
		else if("새마을호".equals(tType)){
			charge = tDao.saemaeulTrain_charge(dpStation, avStation);
		}
		else if("KTX".equals(tType)){
			charge = tDao.ktxTrain_charge(dpStation, avStation);
		}
		
		return charge;
		
		
	}
	
	//어른 특실 요금
	public int vipCharge(String tType, String dpStation,String avStation){
		tDao = new TrainDAO();
		int charge = 0;
		if("무궁화호".equals(tType)){
			charge = tDao.sharonTrain_vipCharge(dpStation, avStation);
		}
		else if("새마을호".equals(tType)){
			charge = tDao.saemaeulTrain_vipCharge(dpStation, avStation);
		}
		else if("KTX".equals(tType)){
			charge = tDao.ktxTrain_vipCharge(dpStation, avStation);
		}
		return charge;
		
		
	}
	
	
	//어린이 일반실 요금
	public int cildrenStandardCharge(String tType, String dpStation,String avStation){
		tDao = new TrainDAO();
		int charge = 0;
		
		charge = (int)(standardCharge(tType,dpStation,avStation)*0.5);
		
		return charge;
		
		
	}
	
	//어린이 특실 요금
	public int cildrenVipCharge(String tType, String dpStation,String avStation){
		tDao = new TrainDAO();
		int charge = 0;
		charge = (int)(vipCharge(tType,dpStation,avStation)*0.5);
		
		return charge;
		
		
	}
	
	
	public String result_standardCharge(String adult,String chliren, int aCharge,int cCharge){
		
		int ad = 0;
		int ch = 0;
		int sCharge=0;
		String result_charge =null;
		
		if(adult==null){
			ch = Integer.parseInt(chliren);
			sCharge = ch*cCharge;
			result_charge = sCharge+"";
		}
		else if(chliren==null){
			ad = Integer.parseInt(adult);
			sCharge = ad*aCharge;
			result_charge = sCharge+"";
		}
		else{
			ad = Integer.parseInt(adult);
			ch = Integer.parseInt(chliren);
			sCharge = (ad*aCharge)+(ch*cCharge);
			result_charge = sCharge+"";
		
		}
		return result_charge;
	}
	
	
	public String result_vipCharge(String adult,String chliren, int aCharge,int cCharge){
		
		int ad = 0;
		int ch = 0;
		int sCharge=0;
		String result_charge =null;
		
		if(adult==null){
			ch = Integer.parseInt(chliren);
			sCharge = ch*cCharge;
			result_charge = sCharge+"";
		}
		else if(chliren==null){
			ad = Integer.parseInt(adult);
			sCharge = ad*aCharge;
			result_charge = sCharge+"";
		}
		else{
			ad = Integer.parseInt(adult);
			ch = Integer.parseInt(chliren);
			sCharge = (ad*aCharge)+(ch*cCharge);
			result_charge = sCharge+"";
			
		}
		return result_charge;
	}
	
	
	
	public ChargeListVO charge_search(String tType, String dpStation,String avStation){
		ChargeListVO clVO = new ChargeListVO();
		
		clVO.setVipCharge(vipCharge(tType,dpStation,avStation)+"");
		clVO.setVipChildrenCharge(cildrenVipCharge(tType,dpStation,avStation)+"");
		clVO.setStandardCharge(standardCharge(tType,dpStation,avStation)+"");
		clVO.setStandardChildrenCharge(cildrenStandardCharge(tType,dpStation,avStation)+"");
		
		
		
		
		
		return clVO;
	}
	
	
	public ArrayList<TableCheckListVO> tableList(ArrayList<SeatVO> sList,String charge,String childrenCharge,int result_people,String adult_people,String children_people,String arrival_time,String time_time){
		ArrayList<TableCheckListVO> tList = new ArrayList<TableCheckListVO>();
		TableCheckListVO tvo = null;
		int adult_people_int = Integer.parseInt(adult_people);
		
		
		for(int i=0; i<result_people; i++){
			tvo = new TableCheckListVO();
			tvo.setSeat_seat(sList.get(i).getSeat_seat());
			tvo.setSeat_number(sList.get(i).getSeat_number());
					
			
			if((adult_people_int-i)>0){
				tvo.setPeople("어른");
				tvo.setCharge(charge);
			}
			else{
				tvo.setPeople("어린이");
				tvo.setCharge(childrenCharge);
			}
			
			tvo.setArrival_time(arrival_time);
			tvo.setTime_time(time_time);
			
			
			tList.add(tvo);
		}
		
		
		
		
		return tList;
		
	}
	
	
	public void bookingResult(ArrayList<TableCheckListVO> tList,String email, String train_type, String depart_station, String last_station, String train_date){
										// tList=어른,어린이 ,돈 ,도착시간 ,좌석번호,좌석번호,좌석(특실,일반실)  이메일, 새마을호, 서울,부산 ,날짜
		TrainBookVO tbvo = new TrainBookVO();
		ArrayList<CodeSearchVO> codeList = new ArrayList<CodeSearchVO>();
		tDao = new TrainDAO();
		CodeSearchVO codeVO = null;
		boolean check = false;
		int booking_code =tDao.booking(email);
		
		
		for(int i=0; i<tList.size(); i++){
			codeVO = new CodeSearchVO();
			codeVO=tDao.searchCode(depart_station,last_station,train_type,tList.get(i).getTime_time() ,tList.get(i).getSeat_number());
			check = tDao.bookingTrain(codeVO,booking_code,tList.get(i).getCharge(),tList.get(i).getPeople(),tList.get(i).getArrival_time(),train_date);
			if(check){
				
			}
		}
		
		
		
	}

}
