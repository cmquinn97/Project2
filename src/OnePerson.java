import java.util.ArrayList;

class OnePerson{
	int id, gender, time;
	static int timeTotal, departure = 0;
	String genderChar;
	
	

	OnePerson(int id, int gender, int time){
		this.id = id;
		this.gender = gender;
		this.time = time;
		
		if (this.gender == 0){
			genderChar = " (F)";
		}else{
			genderChar = " (M)";
		}
	}
	
	
	public void Arrive(ArrayList<OnePerson> queue){
		queue.add(this);
		System.out.println("Person " + this.id + " has arrived." + genderChar);
		
	}
	
	public void UseFacilities(ArrayList<OnePerson> queue, ArrayList<OnePerson> bRoom){
		
		bRoom.add(this);
		System.out.println("Time: "+ timeTotal + "; Person " + this.id + genderChar + " Enters the facilities for " + this.time + " minutes");
		this.time = this.time + timeTotal;
		queue.remove(this);
		try{
			if(!bRoom.isEmpty()&& bRoom.size() < 2){
				Lists.NextUser(queue, bRoom).UseFacilities(queue, bRoom);
			}	
		}catch(IndexOutOfBoundsException e){}	
	}
	
	
	public void Depart(ArrayList<OnePerson> bRoom,ArrayList<OnePerson> queue){
		bRoom.remove(this);
		departure++;
		System.out.println("Time: "+ timeTotal + "; Person " + this.id + genderChar + " exits." + " Departure = " + departure);
		
	}
}