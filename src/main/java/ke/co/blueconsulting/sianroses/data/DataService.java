package ke.co.blueconsulting.sianroses.data;

public interface DataService {
	
	interface GetCallback<SR> {
		
		/**
		 * Called if the operation completes successfully.
		 *
		 * @param response The returned response
		 */
		void onCompleted(SR response);
		
		/**
		 * Called if the operation fails.
		 *
		 * @param t The exception information
		 */
		void onError(Throwable t);
		
		/**
		 * Called at at all times doesn't matter if the operation was successful
		 * or not
		 */
		void always();
	}
	
}
