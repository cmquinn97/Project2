import java.util.ArrayList;
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
				queue.bRoom.get(0).Depart(queue.bRoom, queue.queue);
			}
			
		
	}

}


class OnePerson{
	int id, gender, time;
	static int facilities;

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
		System.out.println(this.id + "Enters" + this.time);
		queue.remove(this);
		try{
			if((queue.get(0).gender == this.gender) && bRoom.size()<3) {
				queue.get(0).UseFacilities(queue, bRoom);
			}
		}catch(IndexOutOfBoundsException e){
			
		}
		
	}
	
	public void Depart(ArrayList<OnePerson> bRoom,ArrayList<OnePerson> queue ){
		bRoom.remove(0);
		System.out.println(this.id + " left");
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


