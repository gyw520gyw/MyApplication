package demo.gyw.com.myapplication.db.generator;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * author: gyw
 * date: 2016/5/19.
 */
public class GreenDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "demo.gyw.com.myapplication.db.model");
        schema.setDefaultJavaPackageDao("demo.gyw.com.myapplication.db.dao");
        schema.enableKeepSectionsByDefault();
        //schema.enableActiveEntitiesByDefault();
        //ActiveRecord
        addEntity(schema);
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    private static void addEntity(Schema schema) {
        Entity person = schema.addEntity("Person");
        person.addIdProperty().primaryKey();
        person.addStringProperty("name");
        person.addIntProperty("age");
        person.addDoubleProperty("height");
        person.addDoubleProperty("weight");

        Entity card = schema.addEntity("Card");
        card.addIdProperty().primaryKey();
        card.addStringProperty("num");
        card.addStringProperty("address");

        Property idcardPK = person.addLongProperty("cardId").getProperty();
        person.addToOne(card, idcardPK);

        Property personPK = card.addLongProperty("personId").getProperty();
        card.addToOne(person,personPK);
    }
}
