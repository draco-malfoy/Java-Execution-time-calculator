/*
 * All versions of Java
 */
class Stopwatch {
	private static String name;
	private static int methodCount = 0;
	private static long start;
	private static long end;
	private static Map<String, Long> map = new HashMap<>();

	public static void start() {
		start("Unnamed Method " + ++methodCount);
	}

	public static void start(String name) {
		Stopwatch.name = name;
		start = System.nanoTime();
	}

	public static void end() {
		end = System.nanoTime() - start;
		map.put(name, end);

		System.out.println("--------------------------------");
		System.out.println(name + " : " + end + "ms");
		System.out.println("--------------------------------");
	}

	public static void summary() {
		System.out.println("-------------------------------------------");
		System.out.println("Method - Time");
		for (Map.Entry<String, Long> entry : map.entrySet())
			System.out.println(entry.getKey() + " - " + entry.getValue() + "ms");
		System.out.println("-------------------------------------------");
	}

	public static void sort() {
		List<Entry<String, Long>> entryList = new ArrayList<Entry<String, Long>>(map.entrySet());
		Collections.sort(entryList, new Comparator<Entry<String, Long>>() {
			@Override
			public int compare(Entry<String, Long> obj1, Entry<String, Long> obj2) {
				return obj1.getValue().compareTo(obj2.getValue());
			}

		});
		System.out.println("-------------------------------------------");
		System.out.println("Method - Time");
		for (Entry<String, Long> entry : entryList)
			System.out.println(entry.getKey() + " - " + entry.getValue() + "ms");
		System.out.println("-------------------------------------------");
	}
}