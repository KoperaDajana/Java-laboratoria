//A. typ wyliczeniowy dla NEW, USED, REFURBISHED
public enum ItemCondition
{
    NEW {
        @Override public String toString() {
            return "Nowy";
        }
    },
    USED {
        @Override public String toString() {
            return "Używany";
        }
    },
    REFURBISHED {
        @Override public String toString() {
            return "Odnowiony";
        }
    }
}
