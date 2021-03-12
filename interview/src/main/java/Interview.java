public class Interview {
        public static void main(String[] args) {
                ArrayListMy<String> list = new ArrayListMy<String>();
                list.add(0, "1");
                String valArrListMy = list.get(0);
                System.out.println(valArrListMy);

                LinkedListMy<String> listLinked = new LinkedListMy<String>();
                listLinked.add("123");
                String valLinkedListMy = listLinked.get(0);
                System.out.println(valLinkedListMy);

        }
}
