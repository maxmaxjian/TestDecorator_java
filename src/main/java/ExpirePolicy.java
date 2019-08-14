public interface ExpirePolicy {

    CallbackEvent<Boolean> getWallpaperExpiredEvent();

    boolean isExpired();

    void destroy();

}
