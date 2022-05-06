import com.gu.sports.api.{PAEventAPI, StatusAPI}
import com.gu.sports.services.PAEventService
import org.scalatra._

import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new PAEventAPI(new PAEventService), "/sports-event/*")
    context.mount(new StatusAPI, "/status/*")
  }
}
