package mobi.letsplay.livescore.modelmanager;

public interface ModelManagerListener {

	public void onError();

	public void onSuccess(Object object);
}
