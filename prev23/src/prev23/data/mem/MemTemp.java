package prev23.data.mem;

import java.util.Objects;

/**
 * A temporary variable.
 */
public class MemTemp {

	/** The name of a temporary variable. */
	public final long temp;

	/** Counter of temporary variables. */
	private static long count = 0;

	/** Creates a new temporary variable. */
	public MemTemp() {
		this.temp = count;
		count++;
	}

	@Override
	public String toString() {
		return "T" + temp;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemTemp memTemp = (MemTemp) o;
        return temp == memTemp.temp;
    }

    @Override
    public int hashCode() {
        return (int)(temp);
    }
}
