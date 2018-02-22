import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		
		Random r = new Random();

		Lists queue = new Lists();
		for(int i = 0; i<10; i++){
			
			int gender; float chance = r.nextFloat(); int time = r.nextInt(7-3) + 3;
			
			if (chance <= .60f){
				gender = 0;
			}else{
				gender = 1;
			}
			
			OnePerson person = new OnePerson(i, gender, time);
			person.Arrive(queue.queue);
		}
			queue.queue.get(0).UseFacilities(queue.queue, queue.bRoom);
			while(queue.bRoom.size()>0){
				int remove = 0;
				int lowTime = 100;
				for (OnePerson person : queue.bRoom){
					if(person.time < lowTime ){
						lowTime = person.time;
						remove = queue.bRoom.indexOf(person);
					}
					
				}
				queue.bRoom.get(remove).Depart(queue.bRoom, queue.queue, remove);
			}
			
		
	}

}


class OnePerson{
	int id, gender, time;
	static int timeTotal;

	OnePerson(int id, int gender, int time){
		this.id = id;
		this.gender = gender;
		this.time = time;
	}
	
	public void Arrive(ArrayList<OnePerson> queue){
		String gender;
		if (this.gender == 0){
			gender = "F";
		}else{
			gender = "M";
		}
		queue.add(this);
		System.out.println("Person " + this.id + " has arrived." + "("+gender+")");
		
	}
	
	public void UseFacilities(ArrayList<OnePerson> queue, ArrayList<OnePerson> bRoom){
		
		bRoom.add(this);
		System.out.println(this.id + " Enters " + this.time + " minutes");
		this.time = this.time + timeTotal;
		queue.remove(this);
		try{
			if((queue.get(0).gender == this.gender) && bRoom.size()<3) {
				queue.get(0).UseFacilities(queue, bRoom);
			}
		}catch(IndexOutOfBoundsException e){
			
		}
		
	}
	
	public void Depart(ArrayList<OnePerson> bRoom,ArrayList<OnePerson> queue, int remove ){
		bRoom.remove(remove);
		timeTotal = timeTotal + (this.time - timeTotal);
		
		System.out.println(this.id + " left " + "Total: " + timeTotal);
		if(!queue.isEmpty()){
			if((bRoom.isEmpty()) || bRoom.get(0).gender == queue.get(0).gender ){
				queue.get(0).UseFacilities(queue, bRoom);
			}
		}
		
	}
}
 
class Lists{
	ArrayList<OnePerson> queue = new ArrayList<OnePerson>();
	ArrayList<OnePerson> bRoom = new ArrayList<OnePerson>();
}




