import _root_.akka.actor.{Props, ActorSystem}

//import actors.CodemotionActor
import com.codemotion.thirteen._
import com.codemotion.thirteen.servlet.{CodemotionActor, MyActorServlet, FrontServlet, JSONServlet}
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {

 val system = ActorSystem()
 val codemotionActor = system.actorOf(Props[CodemotionActor])

  override def init(context: ServletContext) {
    context.mount(new JSONServlet, "/jellybs/*")
    context.mount(new FrontServlet, "/template/*")
    context.mount(new MyActorServlet(system, codemotionActor), "/actors/*")
  }

  override def destroy(context:ServletContext) {
     system.shutdown()
   }
}
