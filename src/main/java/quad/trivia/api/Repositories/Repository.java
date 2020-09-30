package quad.trivia.api.Repositories;

import quad.trivia.api.Services.Implementations.Api.ResultAPIService;
import quad.trivia.api.Services.Implementations.Mock.ResultMockService;
import quad.trivia.api.Services.Service;

public class Repository {

    private static ResultRepository resultRepository;

    /**
     * Returns the resultrepo, even if you somehow forget to initialize the repository
     * @return resultrepository with the desired service
     */
    public static ResultRepository getResultRepository() {
        if(resultRepository == null){
            initialize(Service.Api);
        }
        return resultRepository;
    }

    /**
     * This method is for initializing all the repositories of one kind.
     * @param service: define the kind of service you want to use
     */
    static public void initialize(Service service) {
        switch(service) {
            case Api:
                resultRepository = new ResultRepository(new ResultAPIService());
                break;
            case Mock:
                resultRepository = new ResultRepository(new ResultMockService());
                break;
        }
    }
}
