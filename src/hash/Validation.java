package hash;

/**
 * Created by Flammino on 5/5/2017.
 * Checks required whether required fields are filled
 */
public class Validation {
    /**
     * Checks whether a string has content.
     * @param s
     * @return True if filled, false otherwise
     */
    public boolean checkFilled(String s){
        return !s.isEmpty();
    }
}
