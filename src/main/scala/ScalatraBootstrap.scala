import com.gu.sports.api.{PAEventAPI, StatusAPI}
import org.scalatra._

import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(PAEventAPI, "/sports-event/*")
    context.mount(new StatusAPI, "/status/*")
  }
}
