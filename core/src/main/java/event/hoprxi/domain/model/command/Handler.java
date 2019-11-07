package event.hoprxi.domain.model.command;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 2019-05-28
 */
public interface Handler<T extends Command> {
    void execute(T command);

    void undo();
}
