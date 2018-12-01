package processing.data;


/**
 * Internal sorter used by several data classes.
 * Advanced users only, not official API.
 */
public abstract class Sort implements Runnable {

    /**
     *
     */
    public Sort() { }

    /**
     *
     */
    public void run() {
    int c = size();
    if (c > 1) {
      sort(0, c - 1);
    }
  }

    /**
     *
     * @param i
     * @param j
     */
    protected void sort(int i, int j) {
    int pivotIndex = (i+j)/2;
    swap(pivotIndex, j);
    int k = partition(i-1, j);
    swap(k, j);
    if ((k-i) > 1) sort(i, k-1);
    if ((j-k) > 1) sort(k+1, j);
  }

    /**
     *
     * @param left
     * @param right
     * @return
     */
    protected int partition(int left, int right) {
    int pivot = right;
    do {
      while (compare(++left, pivot) < 0) { }
      while ((right != 0) && (compare(--right, pivot) > 0)) { }
      swap(left, right);
    } while (left < right);
    swap(left, right);
    return left;
  }

    /**
     *
     * @return
     */
    abstract public int size();

    /**
     *
     * @param a
     * @param b
     * @return
     */
    abstract public float compare(int a, int b);

    /**
     *
     * @param a
     * @param b
     */
    abstract public void swap(int a, int b);
}