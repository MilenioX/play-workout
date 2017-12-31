package actions;

import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Action;
import play.mvc.Result;
import utils.ExceptionMailer;
import java.util.concurrent.CompletionStage;
import annotations.Catch;
import java.lang.annotation.*;

public class CatchAction extends Action<Catch> {

    public CompletionStage<Result> call(Http.Context ctx) {
        try {
            return delegate.call(ctx);
        } catch(Throwable e) {
//            if (configuration.send) {
                ExceptionMailer.send(e);
//            } else {
                e.printStackTrace();
                throw new RuntimeException(e);
//            }
        }
    }

}