package me.lab6.server.commands;

import me.lab6.common.Response;
import me.lab6.common.utility.DataType;
import me.lab6.common.utility.Limitations;
import me.lab6.common.workerRelated.Position;
import me.lab6.common.workerRelated.Worker;
import me.lab6.server.managers.CollectionManager;


public class CountByPosition extends AbstractCommand {
    CollectionManager collectionManager;


    public CountByPosition(CollectionManager collectionManager) {
        super("count_by_position", "prints out the number of elements with Position field value equal to given.");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(String arg) {
        Long key = Long.parseLong(arg);
        Position position = Position.valueOf(String.valueOf(key));
        int count = 0;
        for (Worker w : collectionManager.getWorkerMap().values()) {
            if (w.getPosition() == position) {
                count++;
            }
        }
        if (count == 0) {
            return new Response("The collection doesn't contain elements with such position value.");
        } else {
            return new Response("The collection contains " + count + " element(s) with position = " + position + ".");
        }
    }

    @Override
    public Limitations argLimitations() {
//        return new DataLimitations(DataType.POSITION,
//                UserInteractionManager.wrongArgMessage(this) +
//                        "Please, use a proper Position value (" + Position.allPositions() + ").",
//                UserInteractionManager.noArgMessage(this)).limitations();
//    }
//}
        return null;
    }
}