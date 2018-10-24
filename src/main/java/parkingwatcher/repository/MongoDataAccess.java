package parkingwatcher.repository;

import com.mongodb.*;
import parkingwatcher.model.ParkedVehicles;

import java.net.UnknownHostException;

public class MongoDataAccess implements IDataAccess {

    public ParkedVehicles mongo_insertParkedVehicle(ParkedVehicles pv) {
        try {
            MongoClient mongoClient = new MongoClient();
            DB database = mongoClient.getDB("db_pw");
            DBCollection collection = database.getCollection("parked_vehicles");

            DBObject parked = new BasicDBObject("typeVehicle", pv.getTypeVehicle())
                    .append("idVehicle", pv.getIdVehicle())
                    .append("entryDate", pv.getEntryDate())
                    .append("exitDate", null)
                    .append("status", pv.getStatus())
                    .append("paidValue", pv.getPaidValue());

            collection.insert(parked);

            mongoClient.close();

            return pv;
        }catch (UnknownHostException uh) {
            System.out.println(uh.toString());
        }
        return null;
    }
}
