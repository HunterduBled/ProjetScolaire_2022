package metier;

import java.io.Serializable;
import java.util.Comparator;

public class ComparaisonFilDeDiscussion implements Comparator<FilDeDiscussion> , Serializable{

	
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(FilDeDiscussion f1, FilDeDiscussion f2) {
		return f2.getPremierMessage().getDate().compareTo(f1.getPremierMessage().getDate());
	}
}