/*
 * Java 8+ only
//  */

Todo

// class Stopwatch {
// 	private static String name;
// 	private static int methodCount = 0;
// 	private static Instant start;
// 	private static Instant end;
// 	private static Map<String, Long> map = new HashMap<>();

// 	public static void start() {
// 		start("Unnamed Method " + ++methodCount);
// 	}

// 	public static void start(String name) {
// 		Stopwatch.name = name;
// 		start = Instant.now();
// 	}

// 	public static void end() {
// 		end = Instant.now();
// 		Duration en4d = Duration.between(start, end);
		
// //	    System.out.format("%dD, %02d:%05d:%02d.%04d \n", between.toDays(),between.toHours(), 
// //        between.toMinutes(), between.getSeconds(), between.toMillis()); //0D, 00:00:01.1001 
// 		map.put(name, end);

// 		System.out.println("--------------------------------");
// 		System.out.println(name + " : " + end + "ms");
// 		System.out.println("--------------------------------");
// 	}

// 	public static void summary() {
// 		System.out.println("-------------------------------------------");
// 		System.out.println("Method - Time");
// 		for (Map.Entry<String, Long> entry : map.entrySet())
// 			System.out.println(entry.getKey() + " - " + entry.getValue() + "ms");
// 		System.out.println("-------------------------------------------");
// 	}

// 	public static void sort() {
// 		List<Entry<String, Long>> entryList = new ArrayList<Entry<String, Long>>(map.entrySet());
// 		Collections.sort(entryList, new Comparator<Entry<String, Long>>() {
// 			@Override
// 			public int compare(Entry<String, Long> obj1, Entry<String, Long> obj2) {
// 				return obj1.getValue().compareTo(obj2.getValue());
// 			}

// 		});
// 		System.out.println("-------------------------------------------");
// 		System.out.println("Method - Time");
// 		for (Entry<String, Long> entry : entryList)
// 			System.out.println(entry.getKey() + " - " + entry.getValue() + "ms");
// 		System.out.println("-------------------------------------------");
// 	}
// }