import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;


class Photo{
    String filename;
    String cityname;
    Date date_taken;

    public Photo(String filename, String cityname, Date date_taken) {
        this.filename = filename;
        this.cityname = cityname;
        this.date_taken = date_taken;
    }

    @Override
    public String toString()
    {
        return filename + ", " + cityname + ", " + date_taken.toString();
    }

}


class PhotoSorter implements Comparator<Photo>
{
    @Override
    public int compare(Photo o1, Photo o2) {
        int compare = o1.cityname.compareTo(o2.cityname);
        if (compare != 0){
            return compare;
        }
        return o1.date_taken.compareTo(o2.date_taken);
    }
//
//    @Override
//    public Comparator<Photo> reversed() {
//        return null;
//    }
//
//    @Override
//    public Comparator<Photo> thenComparing(Comparator<? super Photo> other) {
//        return null;
//    }
//
//    @Override
//    public <U> Comparator<Photo> thenComparing(Function<? super Photo, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
//        return null;
//    }
//
//    @Override
//    public <U extends Comparable<? super U>> Comparator<Photo> thenComparing(Function<? super Photo, ? extends U> keyExtractor) {
//        return null;
//    }
//
//    @Override
//    public Comparator<Photo> thenComparingInt(ToIntFunction<? super Photo> keyExtractor) {
//        return null;
//    }
//
//    @Override
//    public Comparator<Photo> thenComparingLong(ToLongFunction<? super Photo> keyExtractor) {
//        return null;
//    }
//
//    @Override
//    public Comparator<Photo> thenComparingDouble(ToDoubleFunction<? super Photo> keyExtractor) {
//        return null;
//    }
}



class Solution {

    public String solution(String S) {
        ArrayList<Photo> photos = new ArrayList<>();
        for (String line : S.split("\n"))
        {
            String[] comaSplit = line.split(",");
            if(comaSplit.length != 3) {
                continue;
            }

            String filename = comaSplit[0];
            String cityname = comaSplit[1].trim();
            String datestring = comaSplit[2].trim();

            DateFormat format = new SimpleDateFormat("y-M-d H:m:s");
            Date date;
            try {
                date = format.parse(datestring);
            } catch (ParseException e) {
                System.out.println("Invalid date " + datestring);
                continue;
            }

            photos.add(new Photo(filename, cityname, date));
        }

        PhotoSorter sorter = new PhotoSorter();
        photos.sort(sorter);
        for (Photo p : photos){
            System.out.println(p.toString());
        }
        return "";
    }
}