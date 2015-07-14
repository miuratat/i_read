public class Fragment1 extends Fragment {
	
	public Fragment1() {
		
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab1_fragment, container, false);
    }
}