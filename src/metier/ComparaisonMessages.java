package metier;

import java.io.Serializable;
import java.util.Comparator;

public class ComparaisonMessages implements Comparator<Message> , Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Message m1, Message m2) {
		// TODO Auto-generated method stub
		return m1.getDate().compareTo(m2.getDate());

	}
}
