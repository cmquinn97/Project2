import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {
	static boolean one = false;
	static boolean two = false;
	static boolean three = false;
	static int idNum = 0;

	public static void main(String[] args) {
		Lists queue = new Lists();
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Enter the number in parantheses to choose \n (1)Option one: All 20 at once \n (2)Option two: 5 at a time with three delays of 10 \n "
				+ "(3)Option three: 10 at a time with one delay");
		String option = kb.next();
		
		if(option.equals("1")){
			one = true;
		}
		else if(option.equals("2")){
			two = true;
		}
		else if(option.equals("3")){
			three = true;
		}
		
		if(one){
			populateQueue(20, queue);
		}
		if(two){
			populateQueue(5, queue);
		}
		if(three){
			populateQueue(10, queue);
		}
		//If first three in queue are all same gender, they all enter
		if(queue.queue.get(0).gender == queue.queue.get(1).gender && queue.queue.get(1).gender == queue.queue.get(2).gender){
			while(queue.bRoom.size()<2){
				queue.queue.get(0).UseFacilities(queue.queue, queue.bRoom);
			}
		}
		//Starts the simulation
		queue.queue.get(0).UseFacilities(queue.queue, queue.bRoom);
		bRoomProcess(queue);
			
	}
	
	public static void populateQueue(int amount, Lists queue){
		Random r = new Random();
		
		for(int i = 0; i<amount; i++){
			int gender; float chance = r.nextFloat(); int time = r.nextInt(7-3) + 3;
			
			if (chance <= .60f){
				gender = 0;
			}else{
				gender = 1;
			}
			
			OnePerson person = new OnePerson(idNum, gender, time);
			person.Arrive(queue.queue);
			idNum++;
		}
	}
	


	public static void bRoomProcess(Lists queue){
		List<OnePerson> toRemove = new ArrayList<OnePerson>();
		int populatedTimes = 0;
		
		while(OnePerson.departure < 20){
			for(OnePerson p : queue.bRoom){
				if(p.time == OnePerson.timeTotal){
					toRemove.add(p);
				}
			}
			if(toRemove.isEmpty()){
				OnePerson.timeTotal +=1;
			}
			for(OnePerson remove : toRemove){
				remove.Depart(queue.bRoom, queue.queue);
			}
			if(!toRemove.isEmpty()){
				toRemove.removeAll(toRemove);
				
			}
			if(queue.bRoom.size() == 0){
				if(OnePerson.departure == 20){
					break;
				}
				//Checks for the situation where three would be able to enter
				if(queue.queue.size() > 2){
					if(queue.queue.get(0).gender == queue.queue.get(1).gender && queue.queue.get(1).gender == queue.queue.get(2).gender){
						while(queue.bRoom.size()<2){
							queue.queue.get(0).UseFacilities(queue.queue, queue.bRoom);
						}
					}
				}
				
				try{
					Lists.NextUser(queue.queue, queue.bRoom).UseFacilities(queue.queue, queue.bRoom);
				}catch(NullPointerException e){
					continue;
					
				}
				
			}
			if(queue.bRoom.size()<2){
				if(!queue.queue.isEmpty()){
					if(Lists.NextUser(queue.queue, queue.bRoom).gender == queue.bRoom.get(0).gender){
						Lists.NextUser(queue.queue, queue.bRoom).UseFacilities(queue.queue, queue.bRoom);
					}
				}
			}
			if(two == true && OnePerson.timeTotal == 10 && populatedTimes == 0 || two == true && OnePerson.timeTotal == 20 && populatedTimes == 1 || two == true && OnePerson.timeTotal == 30 && populatedTimes == 2){
				populateQueue(5, queue);
				populatedTimes ++;
			}
			if(three == true && OnePerson.timeTotal == 10 && populatedTimes == 0){
				populateQueue(10, queue);
				populatedTimes ++;
			}
			
		}
	}
}



 
class Lists{
	
	ArrayList<OnePerson> queue = new ArrayList<OnePerson>();
	ArrayList<OnePerson> bRoom = new ArrayList<OnePerson>();
	
	public static OnePerson NextUser(ArrayList<OnePerson> queue, ArrayList<OnePerson> bRoom){
		OnePerson returnPerson = null;
			if(bRoom.isEmpty()){
				returnPerson = queue.get(0);
			}
			else{
				int bRoomGen = bRoom.get(0).gender;
				for(OnePerson person : queue){
					if (person.gender == bRoomGen){
						returnPerson = queue.get(queue.indexOf(person));
						break;
					}else{
						returnPerson = queue.get(0);
					}
				}
			}
		return returnPerson;
	}
}




