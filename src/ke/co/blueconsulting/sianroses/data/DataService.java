package ke.co.blueconsulting.sianroses.data;

public interface DataService<SR> {
	
	interface GetCallback<SR> {
		
		/**
		 * Called if the operation completes successfully.
		 *
		 * @param serverResponse The returned serverResponse
		 */
		void onCompleted(SR serverResponse);
		
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
