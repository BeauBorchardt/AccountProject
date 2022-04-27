package AccountModelPkg;

public class AccessLevel {
    int accessId;
    String accessType;

    @Override
    public String toString() {
        return "AccessLevelMod{" +
                "accessId=" + accessId +
                ", accessType='" + accessType + '\'' +
                '}';
    }
}
