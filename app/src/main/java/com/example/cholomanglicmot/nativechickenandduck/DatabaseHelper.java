package com.example.cholomanglicmot.nativechickenandduck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper db;

    public static final String DATABASE_NAME = "Project.db";
    public static final String TABLE_PROFILE = "profile_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "BREED";
    public static final String COL_3 = "REGION";
    public static final String COL_4 = "PROVINCE";
    public static final String COL_5 = "TOWN";
    public static final String COL_6 = "BARANGAY";
    public static final String COL_7 = "PHONE";
    public static final String COL_8 = "EMAIL";

    public static final String TABLE_PEN = "pen_table";
    public static final String PEN_COL_0 = "ID";
    public static final String PEN_COL_1 = "PEN_NUMBER";
    public static final String PEN_COL_2 = "PEN_TYPE";
    public static final String PEN_COL_3 = "PEN_CURRENT_CAPACITY";
    public static final String PEN_COL_4 = "PEN_TOTAL_CAPACITY";
    public static final String PEN_COL_5 = "PEN_FARM_ID";
    public static final String PEN_COL_6 = "PEN_IS_ACTIVE";


    public static final String TABLE_GENERATION = "generation_table";
    public static final String GENERATION_COL_0 = "ID";
    public static final String GENERATION_COL_1 = "farm_id";
    public static final String GENERATION_COL_2 = "GENERATION_NUMBER";
    public static final String GENERATION_COL_3 = "numerical_generation";
    public static final String GENERATION_COL_4 = "is_active";
    public static final String GENERATION_COL_5 = "deleted_at";



    public static final String TABLE_LINE = "line_table";
    public static final String LINE_COL_0 = "ID";
    public static final String LINE_COL_1 = "LINE_NUMBER";
    public static final String LINE_COL_2 = "LINE_GENERATION";


    public static final String TABLE_FAMILY = "family_table";
    public static final String FAMILY_COL_0 = "ID";
    public static final String FAMILY_COL_1 = "FAMILY_NUMBER";
    public static final String FAMILY_COL_2 = "FAMILY_LINE";





    public static final String TABLE_BROODER = "brooder_table";
    public static final String BROODER_COL_0 = "ID";
    public static final String BROODER_COL_1 = "BROODER_FAMILY";
    public static final String BROODER_COL_2 = "BROODER_LINE";
    public static final String BROODER_COL_3 = "BROODER_GENERATION";
    public static final String BROODER_COL_4 = "BROODER_DATE_ADDED";
    public static final String BROODER_COL_5 = "BROODER_DELETED_AT";

    public static final String TABLE_BROODER_INVENTORIES = "brooder_inventory_table";
    public static final String BROODER_INV_COL_0 = "ID";
    public static final String BROODER_INV_COL_1 = "BROODER_INV_BROODER_ID";
    public static final String BROODER_INV_COL_2 = "BROODER_INV_PEN_NUMBER"; //REFERENCES SA PEN
    public static final String BROODER_INV_COL_3 = "BROODER_INV_BROODER_TAG";
    public static final String BROODER_INV_COL_4 = "BROODER_INV_BATCHING_DATE";
    public static final String BROODER_INV_COL_5 = "BROODER_INV_NUMBER_MALE";
    public static final String BROODER_INV_COL_6 = "BROODER_INV_NUMBER_FEMALE";
    public static final String BROODER_INV_COL_7 = "BROODER_INV_TOTAL";
    public static final String BROODER_INV_COL_8 = "BROODER_INV_LAST_UPDATE";
    public static final String BROODER_INV_COL_9 = "BROODER_INV_DELETED_AT";

    public static final String TABLE_BROODER_FEEDING_RECORDS = "brooder_feeding_records";
    public static final String BROODER_FEEDING_COL_0 = "ID";
    public static final String BROODER_FEEDING_COL_1 = "BROODER_FEEDING_INVENTORY_ID"; //REFERENCE SA BROODER INV ID
    public static final String BROODER_FEEDING_COL_2 = "BROODER_FEEDING_DATE_COLLECTED";
    public static final String BROODER_FEEDING_COL_3 = "BROODER_FEEDING_AMOUNT_OFFERED";
    public static final String BROODER_FEEDING_COL_4 = "BROODER_FEEDING_AMOUNT_REFUSED";
    public static final String BROODER_FEEDING_COL_5 = "BROODER_FEEDING_REMARKS";
    public static final String BROODER_FEEDING_COL_6 = "BROODER_FEEDING_DELETED_AT";

    public static final String TABLE_BROODER_GROWTH_RECORDS = "brooder_growth_records";
    public static final String BROODER_GROWTH_COL_0 = "ID";
    public static final String BROODER_GROWTH_COL_1 = "BROODER_GROWTH_INVENTORY_ID"; //REFEREC=BCE SA BROODER INV ID
    public static final String BROODER_GROWTH_COL_2 = "BROODER_GROWTH_COLLECTION_DAY";
    public static final String BROODER_GROWTH_COL_3 = "BROODER_GROWTH_DATE_COLLECTED";
    public static final String BROODER_GROWTH_COL_4 = "BROODER_GROWTH_MALE_QUANTITY";
    public static final String BROODER_GROWTH_COL_5 = "BROODER_GROWTH_MALE_WEIGHT";
    public static final String BROODER_GROWTH_COL_6 = "BROODER_GROWTH_FEMALE_QUANTITY";
    public static final String BROODER_GROWTH_COL_7 = "BROODER_GROWTH_FEMALE_WEIGHT";
    public static final String BROODER_GROWTH_COL_8 = "BROODER_GROWTH_TOTAL_QUANTITY";
    public static final String BROODER_GROWTH_COL_9 = "BROODER_GROWTH_TOTAL_WEIGHT";
    public static final String BROODER_GROWTH_COL_10 = "BROODER_GROWTH_DELETED_AT";



    public static final String TABLE_REPLACEMENT = "replacement_table";
    public static final String REPLACEMENT_COL_0 = "ID";
    public static final String REPLACEMENT_COL_1 = "REPLACEMENT_FAMILY";
    public static final String REPLACEMENT_COL_2 = "REPLACEMENT_LINE";
    public static final String REPLACEMENT_COL_3 = "REPLACEMENT_GENERATION";
    public static final String REPLACEMENT_COL_4 = "REPLACEMENT_DATE_ADDED";
    public static final String REPLACEMENT_COL_5 = "REPLACEMENT_DELETED_AT";

    public static final String TABLE_REPLACEMENT_INVENTORIES = "replacement_inventory_table";
    public static final String REPLACEMENT_INV_COL_0 = "ID";
    public static final String REPLACEMENT_INV_COL_1 = "REPLACEMENT_INV_REPLACEMENT_ID";
    public static final String REPLACEMENT_INV_COL_2 = "REPLACEMENT_INV_PEN_NUMBER"; //REFERENCES SA PEN
    public static final String REPLACEMENT_INV_COL_3 = "REPLACEMENT_INV_REPLACEMENT_TAG";
    public static final String REPLACEMENT_INV_COL_4 = "REPLACEMENT_INV_BATCHING_DATE";
    public static final String REPLACEMENT_INV_COL_5 = "REPLACEMENT_INV_NUMBER_MALE";
    public static final String REPLACEMENT_INV_COL_6 = "REPLACEMENT_INV_NUMBER_FEMALE";
    public static final String REPLACEMENT_INV_COL_7 = "REPLACEMENT_INV_TOTAL";
    public static final String REPLACEMENT_INV_COL_8 = "REPLACEMENT_INV_LAST_UPDATE";
    public static final String REPLACEMENT_INV_COL_9 = "REPLACEMENT_INV_DELETED_AT";


    public static final String TABLE_REPLACEMENT_FEEDING_RECORDS = "replacement_feeding_records";
    public static final String REPLACEMENT_FEEDING_COL_0 = "ID";
    public static final String REPLACEMENT_FEEDING_COL_1 = "REPLACEMENT_FEEDING_INVENTORY_ID"; //REFERENCE SA REPLACEMENT INV ID
    public static final String REPLACEMENT_FEEDING_COL_2 = "REPLACEMENT_FEEDING_DATE_COLLECTED";
    public static final String REPLACEMENT_FEEDING_COL_3 = "REPLACEMENT_FEEDING_AMOUNT_OFFERED";
    public static final String REPLACEMENT_FEEDING_COL_4 = "REPLACEMENT_FEEDING_AMOUNT_REFUSED";
    public static final String REPLACEMENT_FEEDING_COL_5 = "REPLACEMENT_FEEDING_REMARKS";
    public static final String REPLACEMENT_FEEDING_COL_6 = "REPLACEMENT_FEEDING_DELETED_AT";

    public static final String TABLE_REPLACEMENT_GROWTH_RECORDS = "replacement_growth_records";
    public static final String REPLACEMENT_GROWTH_COL_0 = "ID";
    public static final String REPLACEMENT_GROWTH_COL_1 = "REPLACEMENT_GROWTH_INVENTORY_ID"; //REFEREC=BCE SA REPLACEMENT INV ID
    public static final String REPLACEMENT_GROWTH_COL_2 = "REPLACEMENT_GROWTH_COLLECTION_DAY";
    public static final String REPLACEMENT_GROWTH_COL_3 = "REPLACEMENT_GROWTH_DATE_COLLECTED";
    public static final String REPLACEMENT_GROWTH_COL_4 = "REPLACEMENT_GROWTH_MALE_QUANTITY";
    public static final String REPLACEMENT_GROWTH_COL_5 = "REPLACEMENT_GROWTH_MALE_WEIGHT";
    public static final String REPLACEMENT_GROWTH_COL_6 = "REPLACEMENT_GROWTH_FEMALE_QUANTITY";
    public static final String REPLACEMENT_GROWTH_COL_7 = "REPLACEMENT_GROWTH_FEMALE_WEIGHT";
    public static final String REPLACEMENT_GROWTH_COL_8 = "REPLACEMENT_GROWTH_TOTAL_QUANTITY";
    public static final String REPLACEMENT_GROWTH_COL_9 = "REPLACEMENT_GROWTH_TOTAL_WEIGHT";
    public static final String REPLACEMENT_GROWTH_COL_10 = "REPLACEMENT_GROWTH_DELETED_AT";

    public static final String TABLE_REPLACEMENT_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS = "replacement_pheno_morpho_records";
    public static final String REPLACEMENT_PHENO_MORPHO_COL_0 = "ID";
    public static final String REPLACEMENT_PHENO_MORPHO_COL_1 = "REPLACEMENT_PHENO_MORPHO_INVENTORY_ID";
    public static final String REPLACEMENT_PHENO_MORPHO_COL_2 = "REPLACEMENT_PHENO_MORPHO_COLLECTED";
    public static final String REPLACEMENT_PHENO_MORPHO_COL_3 = "REPLACEMENT_PHENO_MORPHO_SEX";
    public static final String REPLACEMENT_PHENO_MORPHO_COL_4 = "REPLACEMENT_PHENO_MORPHO_TAG_OR_REGISTRY";
    public static final String REPLACEMENT_PHENO_MORPHO_COL_5 = "REPLACEMENT_PHENO_RECORD";
    public static final String REPLACEMENT_PHENO_MORPHO_COL_6 = "REPLACEMENT_MORPHO_RECORD";



    public static final String TABLE_BREEDER = "breeder_table";
    public static final String BREEDER_COL_0 = "ID";
    public static final String BREEDER_COL_1 = "BREEDER_FAMILY";
    public static final String BREEDER_COL_2 = "BREEDER_FEMALE_FAMILY";
    public static final String BREEDER_COL_3 = "BREEDER_LINE";
    public static final String BREEDER_COL_4 = "BREEDER_GENERATION";
    public static final String BREEDER_COL_5 = "BREEDER_DATE_ADDED";
    public static final String BREEDER_COL_6 = "BREEDER_DELETED_AT";

    public static final String TABLE_BREEDER_INVENTORIES = "breeder_inventory_table";
    public static final String BREEDER_INV_COL_0 = "ID";
    public static final String BREEDER_INV_COL_1 = "BREEDER_INV_BREEDER_ID";
    public static final String BREEDER_INV_COL_2 = "BREEDER_INV_PEN_NUMBER";
    public static final String BREEDER_INV_COL_3 = "BREEDER_INV_BREEDER_TAG";
    public static final String BREEDER_INV_COL_4 = "BREEDER_INV_BATCHING_DATE";
    public static final String BREEDER_INV_COL_5 = "BREEDER_INV_NUMBER_MALE";
    public static final String BREEDER_INV_COL_6 = "BREEDER_INV_NUMBER_FEMALE";
    public static final String BREEDER_INV_COL_7 = "BREEDER_INV_TOTAL";
    public static final String BREEDER_INV_COL_8 = "BREEDER_INV_LAST_UPDATE";
    public static final String BREEDER_INV_COL_9 = "BREEDER_INV_DELETED_AT";

    public static final String TABLE_BREEDER_FEEDING_RECORDS = "breeder_feeding_records";
    public static final String BREEDER_FEEDING_COL_0 = "ID";
    public static final String BREEDER_FEEDING_COL_1 = "BREEDER_FEEDING_INVENTORY_ID"; //REFERENCE SA BROODER INV ID
    public static final String BREEDER_FEEDING_COL_2 = "BREEDER_FEEDING_DATE_COLLECTED";
    public static final String BREEDER_FEEDING_COL_3 = "BREEDER_FEEDING_AMOUNT_OFFERED";
    public static final String BREEDER_FEEDING_COL_4 = "BREEDER_FEEDING_AMOUNT_REFUSED";
    public static final String BREEDER_FEEDING_COL_5 = "BREEDER_FEEDING_REMARKS";
    public static final String BREEDER_FEEDING_COL_6 = "BREEDER_FEEDING_DELETED_AT";

    public static final String TABLE_BREEDER_GROWTH_RECORDS = "breeder_growth_records";
    public static final String BREEDER_GROWTH_COL_0 = "ID";
    public static final String BREEDER_GROWTH_COL_1 = "BREEDER_GROWTH_INVENTORY_ID"; //REFEREC=BCE SA BROODER INV ID
    public static final String BREEDER_GROWTH_COL_2 = "BREEDER_GROWTH_COLLECTION_DAY";
    public static final String BREEDER_GROWTH_COL_3 = "BREEDER_GROWTH_DATE_COLLECTED";
    public static final String BREEDER_GROWTH_COL_4 = "BREEDER_GROWTH_MALE_QUANTITY";
    public static final String BREEDER_GROWTH_COL_5 = "BREEDER_GROWTH_MALE_WEIGHT";
    public static final String BREEDER_GROWTH_COL_6 = "BREEDER_GROWTH_FEMALE_QUANTITY";
    public static final String BREEDER_GROWTH_COL_7 = "BREEDER_GROWTH_FEMALE_WEIGHT";
    public static final String BREEDER_GROWTH_COL_8 = "BREEDER_GROWTH_TOTAL_QUANTITY";
    public static final String BREEDER_GROWTH_COL_9 = "BREEDER_GROWTH_TOTAL_WEIGHT";
    public static final String BREEDER_GROWTH_COL_10 = "BREEDER_GROWTH_DELETED_AT";

    public static final String TABLE_BREEDER_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS = "breeder_pheno_morpho_records";
    public static final String BREEDER_PHENO_MORPHO_COL_0 = "ID";
    public static final String BREEDER_PHENO_MORPHO_COL_1  = "BREEDER_PHENO_MORPHO_INVENTORY_ID";
    public static final String BREEDER_PHENO_MORPHO_COL_2  = "BREEDER_PHENO_MORPHO_COLLECTED";
    public static final String BREEDER_PHENO_MORPHO_COL_3  = "BREEDER_PHENO_MORPHO_SEX";
    public static final String BREEDER_PHENO_MORPHO_COL_4  = "BREEDER_PHENO_MORPHO_TAG_OR_REGISTRY";
    public static final String BREEDER_PHENO_MORPHO_COL_5  = "BREEDER_PHENO_RECORD";
    public static final String BREEDER_PHENO_MORPHO_COL_6  = "BREEDER_MORPHO_RECORD";

    public static final String TABLE_EGG_PRODUCTION = "egg_production";
    public static final String EGG_PRODUCTION_COL_0 = "ID";
    public static final String EGG_PRODUCTION_COL_1  = "EGG_PRODUCTION_BREEDER_INVENTORY_ID";
    public static final String EGG_PRODUCTION_COL_2  = "EGG_PRODUCTION_DATE";
    public static final String EGG_PRODUCTION_COL_3  = "EGG_PRODUCTION_EGGS_INTACT";
    public static final String EGG_PRODUCTION_COL_4  = "EGG_PRODUCTION_EGG_WEIGHT";
    public static final String EGG_PRODUCTION_COL_5  = "EGG_PRODUCTION_TOTAL_BROKEN";
    public static final String EGG_PRODUCTION_COL_6  = "EGG_PRODUCTION_TOTAL_REJECTS";
    public static final String EGG_PRODUCTION_COL_7 = "EGG_PRODUCTION_REMARKS";
    public static final String EGG_PRODUCTION_COL_8  = "EGG_PRODUCTION_DELETED_AT";

    public static final String TABLE_EGG_QUALITY = "egg_quality";
    public static final String EGG_QUALITY_COL_0 = "ID";
    public static final String EGG_QUALITY_COL_1  = "EGG_QUALITY_BREEDER_INVENTORY_ID";
    public static final String EGG_QUALITY_COL_2  = "EGG_QUALITY_DATE";
    public static final String EGG_QUALITY_COL_3  = "EGG_QUALITY_WEEK";
    public static final String EGG_QUALITY_COL_4  = "EGG_QUALITY_WEIGHT";
    public static final String EGG_QUALITY_COL_5  = "EGG_QUALITY_COLOR";
    public static final String EGG_QUALITY_COL_6  = "EGG_QUALITY_SHAPE";
    public static final String EGG_QUALITY_COL_7  = "EGG_QUALITY_LENGTH";
    public static final String EGG_QUALITY_COL_8  = "EGG_QUALITY_WIDTH";
    public static final String EGG_QUALITY_COL_9 = "EGG_QUALITY_ALBUMENT_HEIGHT";
    public static final String EGG_QUALITY_COL_10  = "EGG_QUALITY_ALBUMENT_WEIGHT";
    public static final String EGG_QUALITY_COL_11  = "EGG_QUALITY_YOLK_WEIGHT";
    public static final String EGG_QUALITY_COL_12  = "EGG_QUALITY_YOLK_COLOR";
    public static final String EGG_QUALITY_COL_13  = "EGG_QUALITY_SHELL_WEIGHT";
    public static final String EGG_QUALITY_COL_14  = "EGG_QUALITY_SHELL_THICKNESS_TOP";
    public static final String EGG_QUALITY_COL_15  = "EGG_QUALITY_SHELL_THICKNESS_MIDDLE";
    public static final String EGG_QUALITY_COL_16  = "EGG_QUALITY_SHELL_THICKNESS_BOTTOM";



    public static final String TABLE_HATCHERY_RECORD = "hatchery_record";
    public static final String HATCHERY_COL_0 = "ID";
    public static final String HATCHERY_COL_1  = "HATCHERY_BREEDER_INVENTORY_ID";
    public static final String HATCHERY_COL_2  = "HATCHERY_DATE";
    public static final String HATCHERY_COL_3  = "HATCHERY__BATCHING_DATE";
    public static final String HATCHERY_COL_4  = "HATCHERY_EGGS_SET";
    public static final String HATCHERY_COL_5  = "HATCHERY_WEEK_LAY";
    public static final String HATCHERY_COL_6  = "HATCHERY_FERTILE";
    public static final String HATCHERY_COL_7  = "HATCHERY_HATCHED";
    public static final String HATCHERY_COL_8 = "HATCHERY_DATE_HATCHED";
    public static final String HATCHERY_COL_9  = "HATCHERY_DELETED_AT";

    public static final String TABLE_MORTALITY_AND_SALES = "mortality_and_sales";
    public static final String MORT_SALES_COL_0 = "ID";
    public static final String MORT_SALES_COL_1  = "MORT_AND_SALES_DATE";
    public static final String MORT_SALES_COL_2  = "MORT_AND_SALES_BREEDER_INV_ID";
    public static final String MORT_SALES_COL_3  = "MORT_AND_SALES_REPLACEMENT_INV_ID";
    public static final String MORT_SALES_COL_4  = "MORT_AND_SALES_BROODER_INV_ID";
    public static final String MORT_SALES_COL_5  = "MORT_AND_SALES_TYPE";
    public static final String MORT_SALES_COL_6  = "MORT_AND_SALES_CATEGORY";
    public static final String MORT_SALES_COL_7  = "MORT_AND_SALES_PRICE";
    public static final String MORT_SALES_COL_8 = "MORT_AND_SALES_MALE";
    public static final String MORT_SALES_COL_9  = "MORT_AND_SALES_FEMALE";
    public static final String MORT_SALES_COL_10  = "MORT_AND_SALES_TOTAL";
    public static final String MORT_SALES_COL_11  = "MORT_AND_SALES_REASON";
    public static final String MORT_SALES_COL_12  = "MORT_AND_SALES_REMARKS";
    public static final String MORT_SALES_COL_13  = "MORT_AND_SALES_DELETED_AT";



    public static final String TABLE_PHENO_MORPHOS = "pheno_morphos";
    public static final String PHENO_MORPHOS_COL_0 = "id";
    public static final String PHENO_MORPHOS_COL_1   = "replacement_inventory";
    public static final String PHENO_MORPHOS_COL_2   = "breeder_inventory";
    public static final String PHENO_MORPHOS_COL_3   = "values_id";
    public static final String PHENO_MORPHOS_COL_4   = "deleted_at";


    public static final String TABLE_PHENO_MORPHO_VALUES = "pheno_morpho_values";
    public static final String PHENO_MORPHO_VALUES_COL_0 = "id";
    public static final String PHENO_MORPHO_VALUES_COL_1   = "gender";
    public static final String PHENO_MORPHO_VALUES_COL_2   = "tag";
    public static final String PHENO_MORPHO_VALUES_COL_3   = "phenotypic";
    public static final String PHENO_MORPHO_VALUES_COL_4   = "morphometric";
    public static final String PHENO_MORPHO_VALUES_COL_5   = "date_collected";
    public static final String PHENO_MORPHO_VALUES_COL_6   = "deleted_at";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 86);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //NOTES SA DATABASE
        //ANG ILAGAY MONG REFERENCE AY YUNG COLUMN NA ID
        db.execSQL("create table " + TABLE_PROFILE +" (ID TEXT PRIMARY KEY,BREED TEXT,REGION TEXT,PROVINCE TEXT,TOWN TEXT,BARANGAY TEXT,PHONE TEXT,EMAIL TEXT)");
        db.execSQL("create table " + TABLE_PEN +" (ID INTEGER PRIMARY KEY, PEN_NUMBER TEXT, PEN_TYPE TEXT,PEN_CURRENT_CAPACITY INTEGER,PEN_TOTAL_CAPACITY INTEGER)");
        db.execSQL("create table " + TABLE_GENERATION +" (ID INTEGER PRIMARY KEY, farm_id INTEGER, GENERATION_NUMBER TEXT, numerical_generation INTEGER ,is_active TEXT, deleted_at TEXT)");

                              /* "generation_table";    "ID";                        "farm_id";     "GENERATION_NUMBER";     "numerical_generation";    "GENERATION_STATUS";     "deleted_at";    */
        db.execSQL("create table " + TABLE_LINE +" (ID INTEGER PRIMARY KEY,LINE_NUMBER TEXT, LINE_GENERATION INTEGER, FOREIGN KEY (LINE_GENERATION) REFERENCES TABLE_GENERATION(ID))");
        db.execSQL("create table " + TABLE_FAMILY +" (ID INTEGER PRIMARY KEY,FAMILY_NUMBER TEXT, FAMILY_LINE INTEGER, FOREIGN KEY (FAMILY_LINE) REFERENCES TABLE_LINE(ID) )");



        db.execSQL("create table " + TABLE_BROODER +" (ID INTEGER PRIMARY KEY, BROODER_FAMILY TEXT REFERENCES TABLE_FAMILY(ID), BROODER_LINE TEXT, BROODER_GENERATION TEXT, BROODER_DATE_ADDED TEXT, BROODER_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_BROODER_INVENTORIES +" (ID INTEGER PRIMARY KEY,BROODER_INV_BROODER_ID TEXT REFERENCES TABLE_BROODER(ID), BROODER_INV_PEN_NUMBER TEXT, BROODER_INV_BROODER_TAG TEXT, BROODER_INV_BATCHING_DATE TEXT, BROODER_INV_NUMBER_MALE INTEGER, BROODER_INV_NUMBER_FEMALE INTEGER, BROODER_INV_TOTAL INTEGER, BROODER_INV_LAST_UPDATE TEXT, BROODER_INV_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_BROODER_FEEDING_RECORDS +" (ID INTEGER PRIMARY KEY, BROODER_FEEDING_INVENTORY_ID TEXT REFERENCES TABLE_BROODER_INVENTORIES(ID), BROODER_FEEDING_DATE_COLLECTED TEXT, BROODER_FEEDING_AMOUNT_OFFERED TEXT, BROODER_FEEDING_AMOUNT_REFUSED INTEGER, BROODER_FEEDING_REMARKS TEXT, BROODER_FEEDING_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_BROODER_GROWTH_RECORDS +" (ID INTEGER PRIMARY KEY, BROODER_GROWTH_INVENTORY_ID TEXT REFERENCES TABLE_BROODER_INVENTORIES(ID), BROODER_GROWTH_COLLECTION_DAY TEXT, BROODER_GROWTH_DATE_COLLECTED TEXT, BROODER_GROWTH_MALE_QUANTITY INTEGER, BROODER_GROWTH_MALE_WEIGHT INTEGER, BROODER_GROWTH_FEMALE_QUANTITY INTEGER, BROODER_GROWTH_FEMALE_WEIGHT INTEGER,BROODER_GROWTH_TOTAL_QUANTITY INTEGER, BROODER_GROWTH_TOTAL_WEIGHT INTEGER,BROODER_GROWTH_DELETED_AT TEXT)");

        db.execSQL("create table " + TABLE_REPLACEMENT +" (ID INTEGER PRIMARY KEY, REPLACEMENT_FAMILY TEXT REFERENCES TABLE_FAMILY(FAMILY_NUMBER), REPLACEMENT_LINE TEXT, REPLACEMENT_GENERATION TEXT, REPLACEMENT_DATE_ADDED TEXT, REPLACEMENT_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_REPLACEMENT_INVENTORIES +" (ID INTEGER PRIMARY KEY,REPLACEMENT_INV_REPLACEMENT_ID TEXT REFERENCES TABLE_REPLACEMENT(ID), REPLACEMENT_INV_PEN_NUMBER TEXT, REPLACEMENT_INV_REPLACEMENT_TAG TEXT, REPLACEMENT_INV_BATCHING_DATE TEXT, REPLACEMENT_INV_NUMBER_MALE INTEGER, REPLACEMENT_INV_NUMBER_FEMALE INTEGER, REPLACEMENT_INV_TOTAL INTEGER, REPLACEMENT_INV_LAST_UPDATE TEXT, REPLACEMENT_INV_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_REPLACEMENT_FEEDING_RECORDS +" (ID INTEGER PRIMARY KEY, REPLACEMENT_FEEDING_INVENTORY_ID TEXT REFERENCES TABLE_REPLACEMENT_INVENTORIES(ID), REPLACEMENT_FEEDING_DATE_COLLECTED TEXT, REPLACEMENT_FEEDING_AMOUNT_OFFERED TEXT, REPLACEMENT_FEEDING_AMOUNT_REFUSED INTEGER, REPLACEMENT_FEEDING_REMARKS TEXT, REPLACEMENT_FEEDING_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_REPLACEMENT_GROWTH_RECORDS +" (ID INTEGER PRIMARY KEY, REPLACEMENT_GROWTH_INVENTORY_ID TEXT REFERENCES TABLE_REPLACEMENT_INVENTORIES(ID), REPLACEMENT_GROWTH_COLLECTION_DAY TEXT, REPLACEMENT_GROWTH_DATE_COLLECTED TEXT, REPLACEMENT_GROWTH_MALE_QUANTITY INTEGER, REPLACEMENT_GROWTH_MALE_WEIGHT INTEGER, REPLACEMENT_GROWTH_FEMALE_QUANTITY INTEGER, REPLACEMENT_GROWTH_FEMALE_WEIGHT INTEGER,REPLACEMENT_GROWTH_TOTAL_QUANTITY INTEGER, REPLACEMENT_GROWTH_TOTAL_WEIGHT INTEGER,REPLACEMENT_GROWTH_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_REPLACEMENT_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS +" (ID INTEGER PRIMARY KEY, REPLACEMENT_PHENO_MORPHO_INVENTORY_ID INTEGER REFERENCES TABLE_REPLACEMENT_INVENTORIES(ID), REPLACEMENT_PHENO_MORPHO_COLLECTED TEXT, REPLACEMENT_PHENO_MORPHO_SEX TEXT, REPLACEMENT_PHENO_MORPHO_TAG_OR_REGISTRY TEXT, REPLACEMENT_PHENO_RECORD TEXT, REPLACEMENT_MORPHO_RECORD TEXT)");

        db.execSQL("create table " + TABLE_BREEDER +" (ID INTEGER PRIMARY KEY, BREEDER_FAMILY TEXT REFERENCES TABLE_FAMILY(ID), BREEDER_FEMALE_FAMILY REFERENCES TABLE_FAMILY(FAMILY),BREEDER_LINE TEXT, BREEDER_GENERATION TEXT, BREEDER_DATE_ADDED TEXT, BREEDER_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_BREEDER_INVENTORIES +" (ID INTEGER PRIMARY KEY,BREEDER_INV_BREEDER_ID TEXT REFERENCES TABLE_BREEDER(ID), BREEDER_INV_PEN_NUMBER TEXT, BREEDER_INV_BREEDER_TAG TEXT, BREEDER_INV_BATCHING_DATE TEXT, BREEDER_INV_NUMBER_MALE INTEGER, BREEDER_INV_NUMBER_FEMALE INTEGER, BREEDER_INV_TOTAL INTEGER, BREEDER_INV_LAST_UPDATE TEXT, BREEDER_INV_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_BREEDER_FEEDING_RECORDS +" (ID INTEGER PRIMARY KEY, BREEDER_FEEDING_INVENTORY_ID TEXT REFERENCES TABLE_BREEDER_INVENTORIES(ID), BREEDER_FEEDING_DATE_COLLECTED TEXT, BREEDER_FEEDING_AMOUNT_OFFERED TEXT, BREEDER_FEEDING_AMOUNT_REFUSED INTEGER, BREEDER_FEEDING_REMARKS TEXT, BREEDER_FEEDING_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_BREEDER_GROWTH_RECORDS +" (ID INTEGER PRIMARY KEY, BREEDER_GROWTH_INVENTORY_ID TEXT REFERENCES TABLE_BREEDER_INVENTORIES(ID), BREEDER_GROWTH_COLLECTION_DAY TEXT, BREEDER_GROWTH_DATE_COLLECTED TEXT, BREEDER_GROWTH_MALE_QUANTITY INTEGER, BREEDER_GROWTH_MALE_WEIGHT INTEGER, BREEDER_GROWTH_FEMALE_QUANTITY INTEGER, BREEDER_GROWTH_FEMALE_WEIGHT INTEGER,BREEDER_GROWTH_TOTAL_QUANTITY INTEGER, BREEDER_GROWTH_TOTAL_WEIGHT INTEGER,BREEDER_GROWTH_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_BREEDER_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS +" (ID INTEGER PRIMARY KEY, BREEDER_PHENO_MORPHO_INVENTORY_ID INTEGER, BREEDER_PHENO_MORPHO_COLLECTED TEXT, BREEDER_PHENO_MORPHO_SEX TEXT, BREEDER_PHENO_MORPHO_TAG_OR_REGISTRY TEXT, BREEDER_PHENO_RECORD TEXT, BREEDER_MORPHO_RECORD TEXT)");

        db.execSQL("create table " + TABLE_EGG_PRODUCTION +" (ID INTEGER PRIMARY KEY, EGG_PRODUCTION_BREEDER_INVENTORY_ID INTEGER REFERENCES TABLE_BREEDER_INVENTORIES(ID), EGG_PRODUCTION_DATE TEXT, EGG_PRODUCTION_EGGS_INTACT INTEGER, EGG_PRODUCTION_EGG_WEIGHT INTEGER, EGG_PRODUCTION_TOTAL_BROKEN INTEGER, EGG_PRODUCTION_TOTAL_REJECTS INTEGER, EGG_PRODUCTION_REMARKS TEXT, EGG_PRODUCTION_DELETED_AT TEXT)");
        db.execSQL("create table " + TABLE_HATCHERY_RECORD +" (ID INTEGER PRIMARY KEY, HATCHERY_BREEDER_INVENTORY_ID INTEGER REFERENCES TABLE_BREEDER_INVENTORIES(ID), HATCHERY_DATE TEXT, HATCHERY__BATCHING_DATE TEXT, HATCHERY_EGGS_SET INTEGER, HATCHERY_WEEK_LAY INTEGER, HATCHERY_FERTILE INTEGER, HATCHERY_HATCHED INTEGER, HATCHERY_DATE_HATCHED TEXT, HATCHERY_DELETED_AT TEXT)");



        db.execSQL("create table " + TABLE_EGG_QUALITY +" (ID INTEGER PRIMARY KEY, EGG_QUALITY_BREEDER_INVENTORY_ID REFERENCES TABLE_BREEDER_INVENTORIES(ID), EGG_QUALITY_DATE TEXT, EGG_QUALITY_WEEK TEXT ,EGG_QUALITY_WEIGHT INTEGER, EGG_QUALITY_COLOR TEXT, EGG_QUALITY_SHAPE TEXT, EGG_QUALITY_LENGTH INTEGER, EGG_QUALITY_WIDTH INTEGER, EGG_QUALITY_ALBUMENT_HEIGHT INTEGER, EGG_QUALITY_ALBUMENT_WEIGHT INTEGER, EGG_QUALITY_YOLK_WEIGHT INTEGER, EGG_QUALITY_YOLK_COLOR TEXT, EGG_QUALITY_SHELL_WEIGHT INTEGER,EGG_QUALITY_SHELL_THICKNESS_TOP INTEGER,EGG_QUALITY_SHELL_THICKNESS_MIDDLE INTEGER,EGG_QUALITY_SHELL_THICKNESS_BOTTOM INTEGER)");
/*                                                       = "ID";                    "EGG_QUALITY_BREEDER_INVENTORY_ID"; =                                        "EGG_QUALITY_DATE"   "EGG_QUALITY_WEEK"; = ; = "EGG_QUALITY_WEIGHT"; = "EGG_QUALITY_COLOR"; = "EGG_QUALITY_SHAPE";  = "EGG_QUALITY_LENGTH"; =       "EGG_QUALITY_WIDTH"; "         EGG_QUALITY_ALBUMENT_HEIGHT";   "EGG_QUALITY_ALBUMENT_WEIGHT";           "EGG_QUALITY_YOLK_WEIGHT";     "EGG_QUALITY_YOLK_COLOR"    ;EGG_QUALITY_SHELL_WEIGHT"; "EGG_QUALITY_SHELL_THICKNESS_TOP";"EGG_QUALITY_SHELL_THICKNESS_MIDDLE";"EGG_QUALITY_SHELL_THICKNESS_BOTTOM";*/
        db.execSQL("create table " + TABLE_MORTALITY_AND_SALES +" (ID INTEGER PRIMARY KEY, MORT_AND_SALES_DATE TEXT, MORT_AND_SALES_BREEDER_INV_ID INTEGER REFERENCES TABLE_BREEDER_INVENTOIES(ID),MORT_AND_SALES_REPLACEMENT_INV_ID INTEGER REFERENCES TABLE_REPLACEMENT_INVENTORIES(ID), MORT_AND_SALES_BROODER_INV_ID INTEGER REFERENCES TABLE_BROODER_INVENTORIES(ID), MORT_AND_SALES_TYPE TEXT, MORT_AND_SALES_CATEGORY TEXT, MORT_AND_SALES_PRICE INTEGER, MORT_AND_SALES_MALE INTEGER,MORT_AND_SALES_FEMALE INTEGER,  MORT_AND_SALES_TOTAL INTEGER, MORT_AND_SALES_REASON TEXT,MORT_AND_SALES_REMARKS TEXT, MORT_AND_SALES_DELETED_AT TEXT)");

                                                                                                                                                                                   /*        "ID";"MORT_AND_SALES_DATE";"MORT_AND_SALES_BREEDER_INV_ID";"MORT_AND_SALES_REPLACEMENT_INV_ID";"MORT_AND_SALES_BROODER_INV_ID";                                                                    "MORT_AND_SALES_TYPE";"MORT_AND_SALES_CATEGORY"; "      MORT_AND_SALES_PRICE";"MORT_AND_SALES_MALE";"               MORT_AND_SALES_FEMALE";"MORT_AND_SALES_TOTAL"; "MORT_AND_SALES_REASON";"MORT_AND_SALES_REMARKS";"MORT_AND_SALES_DELETED_AT";
    */
        db.execSQL("create table "+ TABLE_PHENO_MORPHO_VALUES + "(id INTEGER PRIMARY KEY, gender TEXT, tag TEXT , phenotypic TEXT, morphometric TEXT, date_collected TEXT, deleted_at TEXT)");

        db.execSQL("create table "+ TABLE_PHENO_MORPHOS + "(id INTEGER PRIMARY KEY, replacement_inventory INTEGER REFERENCES TABLE_REPLACEMENT_INVENTORIES(ID), breeder_inventory INTEGER REFERENCES TABLE_BREEDER_INVENTORIES(ID), values_id INTEGER REFERENCES TABLE_PHENO_MORPHO_VALUES(id), deleted_at TEXT    )");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROFILE);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PEN);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_GENERATION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LINE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FAMILY);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BREEDER);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BROODER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BROODER_INVENTORIES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BROODER_FEEDING_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BROODER_GROWTH_RECORDS);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_REPLACEMENT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_REPLACEMENT_INVENTORIES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_REPLACEMENT_FEEDING_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_REPLACEMENT_GROWTH_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_REPLACEMENT_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BREEDER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BREEDER_INVENTORIES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BREEDER_FEEDING_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BREEDER_GROWTH_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BREEDER_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EGG_PRODUCTION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EGG_QUALITY);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HATCHERY_RECORD);


        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MORTALITY_AND_SALES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PHENO_MORPHOS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PHENO_MORPHO_VALUES);



        onCreate(db);
    }

//----------------
    public boolean insertDataPen(String pen_number, String pen_type, Integer pen_current_capacity, Integer pen_total_capacity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PEN_COL_1, pen_number);
        contentValues.put(PEN_COL_2, pen_type);
        contentValues.put(PEN_COL_3, pen_current_capacity);
        contentValues.put(PEN_COL_4, pen_total_capacity);
        long result = db.insert(TABLE_PEN,null,contentValues);
        if (result == -1)
            return  false;
        else
            return true;

    }

//--------------------

    /*    public static final String GENERATION_COL_0 = "ID";
    public static final String GENERATION_COL_1 = "farm_id";
    public static final String GENERATION_COL_2 = "GENERATION_NUMBER";
    public static final String GENERATION_COL_3 = "numerical_generation";
    public static final String GENERATION_COL_4 = "GENERATION_STATUS";
    public static final String GENERATION_COL_5 = "GENERATION_CULL";*/

    /*   boolean isInserted = myDb.insertDataGeneration(generation_number, Integer.parseInt(mInput_generation_number.getText().toString()), "Active");*/
    public boolean insertDataGeneration(String generation_number, Integer numerical_gen, String is_active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GENERATION_COL_1, 0);
        contentValues.put(GENERATION_COL_2, generation_number);
        contentValues.put(GENERATION_COL_3, numerical_gen);
        contentValues.put(GENERATION_COL_4, is_active);
        contentValues.put(GENERATION_COL_5, "0/0/0");


        long result = db.insert(TABLE_GENERATION,null,contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

//--------------------
    public boolean insertDataLine(String line_number, Integer line_generation_number ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(LINE_COL_1, line_number);
        contentValues.put(LINE_COL_2, line_generation_number);



        long result = db.insert(TABLE_LINE,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }
    public boolean insertDataFamily(String family_number, Integer family_line_number ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FAMILY_COL_1, family_number);
        contentValues.put(FAMILY_COL_2, family_line_number);




        long result = db.insert(TABLE_FAMILY,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataBrooder(String brooder_family_number, String brooder_line_number, String brooder_generation_number, String brooder_date_added, String brooder_deleted_at ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BROODER_COL_1, brooder_family_number);
        contentValues.put(BROODER_COL_2, brooder_line_number);
        contentValues.put(BROODER_COL_3, brooder_generation_number);
        contentValues.put(BROODER_COL_4, brooder_date_added);
        contentValues.put(BROODER_COL_5, brooder_deleted_at);

        long result = db.insert(TABLE_BROODER,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }
    public boolean insertDataReplacement(String replacement_family_number, String replacement_line_number, String replacement_generation_number, String replacement_date_added, String replacment_deleted_at ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put(BROODER_COL_0, brooder_id);
        contentValues.put(REPLACEMENT_COL_1, replacement_family_number);
        contentValues.put(REPLACEMENT_COL_2, replacement_line_number);
        contentValues.put(REPLACEMENT_COL_3, replacement_generation_number);
        contentValues.put(REPLACEMENT_COL_4, replacement_date_added);
        contentValues.put(REPLACEMENT_COL_5, replacment_deleted_at);




        long result = db.insert(TABLE_REPLACEMENT,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }
    public boolean insertDataBreeder(String brooder_family_number, String brooder_female_family_id ,String brooder_line_number, String brooder_generation_number, String brooder_date_added, String brooder_deleted_at ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BREEDER_COL_1, brooder_family_number);
        contentValues.put(BREEDER_COL_2, brooder_female_family_id);
        contentValues.put(BREEDER_COL_3, brooder_line_number);
        contentValues.put(BREEDER_COL_4, brooder_generation_number);
        contentValues.put(BREEDER_COL_5, brooder_date_added);
        contentValues.put(BREEDER_COL_6, brooder_deleted_at);

        long result = db.insert(TABLE_BREEDER,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataBrooderInventory(Integer brooder_inv_brooder_id, String brooder_inv_pen_number, String brooder_inv_brooder_tag, String brooder_inv_batching_date, Integer brooder_inv_number_male, Integer brooder_inv_number_female, Integer brooder_inv_total, String brooder_inv_last_update, String brooder_inv_deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BROODER_INV_COL_1, brooder_inv_brooder_id);
        contentValues.put(BROODER_INV_COL_2, brooder_inv_pen_number);
        contentValues.put(BROODER_INV_COL_3, brooder_inv_brooder_tag);
        contentValues.put(BROODER_INV_COL_4, brooder_inv_batching_date);
        contentValues.put(BROODER_INV_COL_5, brooder_inv_number_male);
        contentValues.put(BROODER_INV_COL_6, brooder_inv_number_female);
        contentValues.put(BROODER_INV_COL_7, brooder_inv_total);
        contentValues.put(BROODER_INV_COL_8, brooder_inv_last_update);
        contentValues.put(BROODER_INV_COL_9, brooder_inv_deleted_at);

        long result = db.insert(TABLE_BROODER_INVENTORIES,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataBreederInventory(Integer brooder_inv_brooder_id, String brooder_inv_pen_number, String brooder_inv_brooder_tag, String brooder_inv_batching_date, Integer brooder_inv_number_male, Integer brooder_inv_number_female, Integer brooder_inv_total, String brooder_inv_last_update, String brooder_inv_deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BREEDER_INV_COL_1, brooder_inv_brooder_id);
        contentValues.put(BREEDER_INV_COL_2, brooder_inv_pen_number);
        contentValues.put(BREEDER_INV_COL_3, brooder_inv_brooder_tag);
        contentValues.put(BREEDER_INV_COL_4, brooder_inv_batching_date);
        contentValues.put(BREEDER_INV_COL_5, brooder_inv_number_male);
        contentValues.put(BREEDER_INV_COL_6, brooder_inv_number_female);
        contentValues.put(BREEDER_INV_COL_7, brooder_inv_total);
        contentValues.put(BREEDER_INV_COL_8, brooder_inv_last_update);
        contentValues.put(BREEDER_INV_COL_9, brooder_inv_deleted_at);

        long result = db.insert(TABLE_BREEDER_INVENTORIES,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }
    public boolean insertDataReplacementInventory(Integer brooder_inv_brooder_id, String brooder_inv_pen_number, String brooder_inv_brooder_tag, String brooder_inv_batching_date, Integer brooder_inv_number_male, Integer brooder_inv_number_female, Integer brooder_inv_total, String brooder_inv_last_update, String brooder_inv_deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(REPLACEMENT_INV_COL_1, brooder_inv_brooder_id);
        contentValues.put(REPLACEMENT_INV_COL_2, brooder_inv_pen_number);
        contentValues.put(REPLACEMENT_INV_COL_3, brooder_inv_brooder_tag);
        contentValues.put(REPLACEMENT_INV_COL_4, brooder_inv_batching_date);
        contentValues.put(REPLACEMENT_INV_COL_5, brooder_inv_number_male);
        contentValues.put(REPLACEMENT_INV_COL_6, brooder_inv_number_female);
        contentValues.put(REPLACEMENT_INV_COL_7, brooder_inv_total);
        contentValues.put(REPLACEMENT_INV_COL_8, brooder_inv_last_update);
        contentValues.put(REPLACEMENT_INV_COL_9, brooder_inv_deleted_at);

        long result = db.insert(TABLE_REPLACEMENT_INVENTORIES,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }


    public boolean insertDataBrooderFeedingRecords(Integer brooder_feed_brooder_id, String brooder_feed_date_collected, Float brooder_feed_offered, Float brooder_feed_refused, String brooder_feed_remarks, String brooder_feed_deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BROODER_FEEDING_COL_1, brooder_feed_brooder_id);
        contentValues.put(BROODER_FEEDING_COL_2, brooder_feed_date_collected);
        contentValues.put(BROODER_FEEDING_COL_3, brooder_feed_offered);
        contentValues.put(BROODER_FEEDING_COL_4, brooder_feed_refused);
        contentValues.put(BROODER_FEEDING_COL_5, brooder_feed_remarks);
        contentValues.put(BROODER_FEEDING_COL_6, brooder_feed_deleted_at);


        long result = db.insert(TABLE_BROODER_FEEDING_RECORDS,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataBreederFeedingRecords(Integer brooder_feed_brooder_id, String brooder_feed_date_collected, Float brooder_feed_offered, Float brooder_feed_refused, String brooder_feed_remarks, String brooder_feed_deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BREEDER_FEEDING_COL_1, brooder_feed_brooder_id);
        contentValues.put(BREEDER_FEEDING_COL_2, brooder_feed_date_collected);
        contentValues.put(BREEDER_FEEDING_COL_3, brooder_feed_offered);
        contentValues.put(BREEDER_FEEDING_COL_4, brooder_feed_refused);
        contentValues.put(BREEDER_FEEDING_COL_5, brooder_feed_remarks);
        contentValues.put(BREEDER_FEEDING_COL_6, brooder_feed_deleted_at);


        long result = db.insert(TABLE_BREEDER_FEEDING_RECORDS,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataReplacementFeedingRecords(Integer brooder_feed_brooder_id, String brooder_feed_date_collected, Float brooder_feed_offered, Float brooder_feed_refused, String brooder_feed_remarks, String brooder_feed_deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(REPLACEMENT_FEEDING_COL_1, brooder_feed_brooder_id);
        contentValues.put(REPLACEMENT_FEEDING_COL_2, brooder_feed_date_collected);
        contentValues.put(REPLACEMENT_FEEDING_COL_3, brooder_feed_offered);
        contentValues.put(REPLACEMENT_FEEDING_COL_4, brooder_feed_refused);
        contentValues.put(REPLACEMENT_FEEDING_COL_5, brooder_feed_remarks);
        contentValues.put(REPLACEMENT_FEEDING_COL_6, brooder_feed_deleted_at);


        long result = db.insert(TABLE_REPLACEMENT_FEEDING_RECORDS,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataBrooderGrowthRecords(Integer brooder_growth_brooder_id, Integer brooder_growth_collection_day, String brooder_growth_date_collected, Integer brooder_growth_male_quantity, Float brooder_growth_male_weight, Integer brooder_growth_female_quantity, Float brooder_growth_female_weight, Integer brooder_growth_total_quantity, Float brooder_growth_total_weight, String brooder_growth_deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BROODER_GROWTH_COL_1, brooder_growth_brooder_id);
        contentValues.put(BROODER_GROWTH_COL_2, brooder_growth_collection_day);
        contentValues.put(BROODER_GROWTH_COL_3, brooder_growth_date_collected);
        contentValues.put(BROODER_GROWTH_COL_4, brooder_growth_male_quantity);
        contentValues.put(BROODER_GROWTH_COL_5, brooder_growth_male_weight);
        contentValues.put(BROODER_GROWTH_COL_6, brooder_growth_female_quantity);
        contentValues.put(BROODER_GROWTH_COL_7, brooder_growth_female_weight);
        contentValues.put(BROODER_GROWTH_COL_8, brooder_growth_total_quantity);
        contentValues.put(BROODER_GROWTH_COL_9, brooder_growth_total_weight);
        contentValues.put(BROODER_GROWTH_COL_10, brooder_growth_deleted_at);


        long result = db.insert(TABLE_BROODER_GROWTH_RECORDS,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataBreederGrowthRecords(Integer brooder_growth_brooder_id, Integer brooder_growth_collection_day, String brooder_growth_date_collected, Integer brooder_growth_male_quantity, Float brooder_growth_male_weight, Integer brooder_growth_female_quantity, Float brooder_growth_female_weight, Integer brooder_growth_total_quantity, Float brooder_growth_total_weight, String brooder_growth_deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BREEDER_GROWTH_COL_1, brooder_growth_brooder_id);
        contentValues.put(BREEDER_GROWTH_COL_2, brooder_growth_collection_day);
        contentValues.put(BREEDER_GROWTH_COL_3, brooder_growth_date_collected);
        contentValues.put(BREEDER_GROWTH_COL_4, brooder_growth_male_quantity);
        contentValues.put(BREEDER_GROWTH_COL_5, brooder_growth_male_weight);
        contentValues.put(BREEDER_GROWTH_COL_6, brooder_growth_female_quantity);
        contentValues.put(BREEDER_GROWTH_COL_7, brooder_growth_female_weight);
        contentValues.put(BREEDER_GROWTH_COL_8, brooder_growth_total_quantity);
        contentValues.put(BREEDER_GROWTH_COL_9, brooder_growth_total_weight);
        contentValues.put(BREEDER_GROWTH_COL_10, brooder_growth_deleted_at);


        long result = db.insert(TABLE_BREEDER_GROWTH_RECORDS,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }





    public boolean insertDataReplacementGrowthRecords(Integer brooder_growth_brooder_id, Integer brooder_growth_collection_day, String brooder_growth_date_collected, Integer brooder_growth_male_quantity, Float brooder_growth_male_weight, Integer brooder_growth_female_quantity, Float brooder_growth_female_weight, Integer brooder_growth_total_quantity, Float brooder_growth_total_weight, String brooder_growth_deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(REPLACEMENT_GROWTH_COL_1, brooder_growth_brooder_id);
        contentValues.put(REPLACEMENT_GROWTH_COL_2, brooder_growth_collection_day);
        contentValues.put(REPLACEMENT_GROWTH_COL_3, brooder_growth_date_collected);
        contentValues.put(REPLACEMENT_GROWTH_COL_4, brooder_growth_male_quantity);
        contentValues.put(REPLACEMENT_GROWTH_COL_5, brooder_growth_male_weight);
        contentValues.put(REPLACEMENT_GROWTH_COL_6, brooder_growth_female_quantity);
        contentValues.put(REPLACEMENT_GROWTH_COL_7, brooder_growth_female_weight);
        contentValues.put(REPLACEMENT_GROWTH_COL_8, brooder_growth_total_quantity);
        contentValues.put(REPLACEMENT_GROWTH_COL_9, brooder_growth_total_weight);
        contentValues.put(REPLACEMENT_GROWTH_COL_10, brooder_growth_deleted_at);


        long result = db.insert(TABLE_REPLACEMENT_GROWTH_RECORDS,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }
/*    db.execSQL("create table " + TABLE_REPLACEMENT_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS +" (ID INTEGER PRIMARY KEY, REPLACEMENT_PHENO_MORPHO_INVENTORY_ID INTEGER REFERENCES TABLE_REPLACEMENT_INVENTORIES(ID),  REPLACEMENT_PHENO_MORPHO_COLLECTED TEXT,  REPLACEMENT_PHENO_MORPHO_SEX TEXT, REPLACEMENT_PHENO_MORPHO_TAG_OR_REGISTRY TEXT,REPLACEMENT_PHENO_RECORD TEXT, REPLACEMENT_MORPHO_RECORD TEXT)");
 */

    public boolean insertDataReplacementPhenoMorphoRecords(Integer inv_id,  String date, String sex, String tag, String pheno, String morpho){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(REPLACEMENT_PHENO_MORPHO_COL_1, inv_id);
        contentValues.put(REPLACEMENT_PHENO_MORPHO_COL_2, date);
        contentValues.put(REPLACEMENT_PHENO_MORPHO_COL_3, sex);
        contentValues.put(REPLACEMENT_PHENO_MORPHO_COL_4, tag);
        contentValues.put(REPLACEMENT_PHENO_MORPHO_COL_5, pheno);
        contentValues.put(REPLACEMENT_PHENO_MORPHO_COL_6, morpho);



        long result = db.insert(TABLE_REPLACEMENT_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataBreederPhenoMorphoRecords(Integer inv_id,  String date, String sex, String tag, String pheno, String morpho){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BREEDER_PHENO_MORPHO_COL_1, inv_id);
        contentValues.put(BREEDER_PHENO_MORPHO_COL_2, date);
        contentValues.put(BREEDER_PHENO_MORPHO_COL_3, sex);
        contentValues.put(BREEDER_PHENO_MORPHO_COL_4, tag);
        contentValues.put(BREEDER_PHENO_MORPHO_COL_5, pheno);
        contentValues.put(BREEDER_PHENO_MORPHO_COL_6, morpho);



        long result = db.insert(TABLE_BREEDER_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }
/*    public static final String TABLE_EGG_QUALITY = "egg_quality";
    public static final String EGG_QUALITY_COL_0 = "ID";
    public static final String EGG_QUALITY_COL_1  = "EGG_QUALITY_BREEDER_INVENTORY_ID";
    public static final String EGG_QUALITY_COL_2  = "EGG_QUALITY_DATE";
    public static final String EGG_QUALITY_COL_3  = "EGG_QUALITY_WEIGHT";
    public static final String EGG_QUALITY_COL_4  = "EGG_QUALITY_COLOR";
    public static final String EGG_QUALITY_COL_5  = "EGG_QUALITY_SHAPE";
    public static final String EGG_QUALITY_COL_6  = "EGG_QUALITY_LENGTH";
    public static final String EGG_QUALITY_COL_7  = "EGG_QUALITY_WIDTH";
    public static final String EGG_QUALITY_COL_8 = "EGG_QUALITY_ALBUMENT_HEIGHT";
    public static final String EGG_QUALITY_COL_9  = "EGG_QUALITY_ALBUMENT_WEIGHT";
    public static final String EGG_QUALITY_COL_10  = "EGG_QUALITY_YOLK_WEIGHT";
    public static final String EGG_QUALITY_COL_11  = "EGG_QUALITY_YOLK_COLOR";
    public static final String EGG_QUALITY_COL_12  = "EGG_QUALITY_SHELL_WEIGHT";
    public static final String EGG_QUALITY_COL_13  = "EGG_QUALITY_SHELL_THICKNESS_TOP";
    public static final String EGG_QUALITY_COL_14  = "EGG_QUALITY_SHELL_THICKNESS_MIDDLE";
    public static final String EGG_QUALITY_COL_15  = "EGG_QUALITY_SHELL_THICKNESS_BOTTOM";*/
public boolean insertEggQualityRecords(Integer breeder_inv_id, String date, Integer week,Float weight, String color, String shape, Float length, Float width, Float al_height, Float al_weight, Float yolk_weight, String yolk_color, Float shell_weight, Float shell_thick_top, Float shell_thick_middle, Float shell_thick_bottom){
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues contentValues = new ContentValues();

    contentValues.put(EGG_QUALITY_COL_1, breeder_inv_id);
    contentValues.put(EGG_QUALITY_COL_2, date);
    contentValues.put(EGG_QUALITY_COL_3, week);
    contentValues.put(EGG_QUALITY_COL_4, weight);
    contentValues.put(EGG_QUALITY_COL_5, color);
    contentValues.put(EGG_QUALITY_COL_6, shape);
    contentValues.put(EGG_QUALITY_COL_7, length);
    contentValues.put(EGG_QUALITY_COL_8, width);
    contentValues.put(EGG_QUALITY_COL_9, al_height);
    contentValues.put(EGG_QUALITY_COL_10, al_weight);
    contentValues.put(EGG_QUALITY_COL_11, yolk_weight);
    contentValues.put(EGG_QUALITY_COL_12, yolk_color);
    contentValues.put(EGG_QUALITY_COL_13, shell_weight);
    contentValues.put(EGG_QUALITY_COL_14, shell_thick_top);
    contentValues.put(EGG_QUALITY_COL_15, shell_thick_middle);
    contentValues.put(EGG_QUALITY_COL_16, shell_thick_bottom);



    long result = db.insert(TABLE_EGG_QUALITY,null,contentValues);

    if (result == -1)
        return  false;
    else
        return true;

}
    public boolean insertEggProductionRecords(Integer breeder_inv_id, String date, Integer intact, Float weight, Integer broken, Integer rejects, String remarks, String deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(EGG_PRODUCTION_COL_1, breeder_inv_id);
        contentValues.put(EGG_PRODUCTION_COL_2, date);
        contentValues.put(EGG_PRODUCTION_COL_3, intact);
        contentValues.put(EGG_PRODUCTION_COL_4, weight);
        contentValues.put(EGG_PRODUCTION_COL_5, broken);
        contentValues.put(EGG_PRODUCTION_COL_6, rejects);
        contentValues.put(EGG_PRODUCTION_COL_7, remarks);
        contentValues.put(EGG_PRODUCTION_COL_8, deleted_at);


        long result = db.insert(TABLE_EGG_PRODUCTION,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }
    public boolean insertHatcheryRecords(Integer breeder_inv_id, String date, String batch_date, Integer set, Integer week, Integer fertile, Integer hatched,String date_hatched, String deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(HATCHERY_COL_1, breeder_inv_id);
        contentValues.put(HATCHERY_COL_2, date);
        contentValues.put(HATCHERY_COL_3, batch_date);
        contentValues.put(HATCHERY_COL_4, set);
        contentValues.put(HATCHERY_COL_5, week);
        contentValues.put(HATCHERY_COL_6, fertile);
        contentValues.put(HATCHERY_COL_7, hatched);
        contentValues.put(HATCHERY_COL_8, date_hatched);
        contentValues.put(HATCHERY_COL_9, deleted_at);


        long result = db.insert(TABLE_HATCHERY_RECORD,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertDataMortalityAndSales(String date, Integer breeder_id, Integer replacement_id, Integer brooder_id, String type, String category, Integer price, Integer male_quantity, Integer female_quantity, Integer total, String reason, String remarks, String deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(MORT_SALES_COL_1, date);
        contentValues.put(MORT_SALES_COL_2, breeder_id);
        contentValues.put(MORT_SALES_COL_3, replacement_id);
        contentValues.put(MORT_SALES_COL_4, brooder_id);
        contentValues.put(MORT_SALES_COL_5, type);
        contentValues.put(MORT_SALES_COL_6, category);
        contentValues.put(MORT_SALES_COL_7, price);
        contentValues.put(MORT_SALES_COL_8, male_quantity);
        contentValues.put(MORT_SALES_COL_9, female_quantity);
        contentValues.put(MORT_SALES_COL_10, total);
        contentValues.put(MORT_SALES_COL_11, reason);
        contentValues.put(MORT_SALES_COL_12, remarks);
        contentValues.put(MORT_SALES_COL_13, deleted_at);


        long result = db.insert(TABLE_MORTALITY_AND_SALES,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }


    public boolean insertDataAddress(String region, String province, String town, String barangay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_1, id);
        //contentValues.put(COL_2, breed);
        contentValues.put(COL_3, region);
        contentValues.put(COL_4, province);
        contentValues.put(COL_5, town);
        contentValues.put(COL_6, barangay);
        long result = db.insert(TABLE_PROFILE,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;
    }
    public boolean insertDataContacts(String id, String phone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_7, phone);
        contentValues.put(COL_8, email);
        long result = db.insert(TABLE_PROFILE,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;

    }

    public boolean insertPhenoMorphoRecords(String gender, String tag, String phenotypic, String morphometric, String date_collected, String  deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PHENO_MORPHO_VALUES_COL_1, gender);
        contentValues.put(PHENO_MORPHO_VALUES_COL_2, tag);
        contentValues.put(PHENO_MORPHO_VALUES_COL_3, phenotypic);
        contentValues.put(PHENO_MORPHO_VALUES_COL_4, morphometric);
        contentValues.put(PHENO_MORPHO_VALUES_COL_5, date_collected);
        contentValues.put(PHENO_MORPHO_VALUES_COL_6, deleted_at);
        long result = db.insert(TABLE_PHENO_MORPHO_VALUES,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;
    }

    public boolean insertPhenoMorphos(Integer replacement_inv, Integer breeder_inv, Integer values_id, String deleted_at){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PHENO_MORPHOS_COL_1, replacement_inv);
        contentValues.put(PHENO_MORPHOS_COL_2, breeder_inv);
        contentValues.put(PHENO_MORPHOS_COL_3, values_id);
        contentValues.put(PHENO_MORPHOS_COL_4, deleted_at);
        long result = db.insert(TABLE_PHENO_MORPHOS,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_PEN);

        db.close();
    }

    public Cursor getAllDataFromGeneration(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_GENERATION, null);
        return res;
    }
    public Cursor getAllDataFromLine(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_LINE, null);

        return res;
    }
    public Cursor getAllDataFromFamily(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_FAMILY, null);

        return res;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PROFILE, null);
        return res;
    }

    public Cursor getAllDataFromPen(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PEN, null);
        return res;
    }

    public Cursor getIDFromBroodersWhere(String generation, String line, String family){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BROODER+ " where BROODER_FAMILY is "+family+ " and BROODER_LINE is "+line+" and BROODER_GENERATION is "+generation, null);
        return res;
    }
    public Cursor getBroodersFromPen(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PEN+ " where PEN_TYPE LIKE '%Brooder%'", null);
        return res;
    }
    public Cursor getAllDataFromBrooders(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BROODER, null);

        return res;
    }
    public Cursor getAllDataFromBroodersWhere(String brooder_generation, String brooder_line, String brooder_family){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BROODER + " where BROODER_GENERATION is "+brooder_generation+ " and BROODER_LINE is "+brooder_line+" and BROODER_FAMILY is "+brooder_family, null);

        return res;
    }
    public Cursor getIDFromBroodersInvWhere(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BROODER_INVENTORIES + " where BROODER_INV_BROODER_ID is "+id, null);

        return res;
    }
    public Cursor getAllDataFromBrooderInventory(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BROODER_INVENTORIES, null);

        return res;
    }
    public Cursor getAllDataFromBrooderInventoryWherePen(String pen){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BROODER_INVENTORIES + " where BROODER_INV_PEN_NUMBER is "+pen, null);

        return res;
    }
    public Cursor getDataFromBrooderInventoryWherePenAndID(String tag){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BROODER_INVENTORIES + " where BROODER_INV_BROODER_TAG is ?", new String[] {tag});

        return res;
    }
    public Cursor getAllDataFromBrooderFeedingRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BROODER_FEEDING_RECORDS, null);

        return res;
    }
    public Cursor getAllDataFromBrooderGrowthRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BROODER_GROWTH_RECORDS, null);

        return res;
    }

    public Cursor getDataFromGenerationWhereGenNumber(String number){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_GENERATION+ " where GENERATION_NUMBER is ?", new String[]{number});

        return res;
    }
    public Cursor getDataFromGenerationWhereID(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select GENERATION_NUMBER from " +TABLE_GENERATION+ " where ID is ?", new String[]{id.toString()});

        return res;
    }
    public Cursor getDataFromLineWhereLineNumber(String number){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_LINE+ " where LINE_NUMBER is ?", new String[]{number});

        return res;
    }
    public Cursor getDataFromLineWhereID(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_LINE+ " where ID is ?", new String[]{id.toString()});

        return res;
    }

























    public Cursor getIDFromReplacementsWhere(String generation, String line, String family){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_REPLACEMENT+ " where REPLACEMENT_FAMILY is "+family+ " and REPLACEMENT_LINE is "+line+" and REPLACEMENT_GENERATION is "+generation, null);
        return res;
    }

    public Cursor getReplacementsFromPen(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PEN+ " where PEN_TYPE LIKE '%Grower%'", null);
        return res;
    }
    public Cursor getAllDataFromReplacements(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_REPLACEMENT, null);

        return res;
    }
    public Cursor getAllDataFromReplacementsWhere(String replacement_generation, String replacemet_line, String replacement_family){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_REPLACEMENT + " where REPLACEMENT_GENERATION is "+replacement_generation+ " and REPLACEMENT_LINE is "+replacemet_line+" and REPLACEMENT_FAMILY is "+replacement_family, null);

        return res;
    }
    public Cursor getIDFromReplacementInvWhere(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_REPLACEMENT_INVENTORIES + " where REPLACEMENT_INV_REPLACEMENT_ID is "+id, null);

        return res;
    }
    public Cursor getAllDataFromReplacementInventory(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_REPLACEMENT_INVENTORIES, null);

        return res;
    }
    public Cursor getDataFromReplacementInventoryWhereTag(String tag){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_REPLACEMENT_INVENTORIES + " where REPLACEMENT_INV_REPLACEMENT_TAG is " + tag, null);

        return res;
    }
    public Cursor getAllDataFromReplacementFeedingRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_REPLACEMENT_FEEDING_RECORDS, null);

        return res;
    }

    public Cursor getAllDataFromReplacementPhenoMorphoRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_REPLACEMENT_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS, null);

        return res;
    }
    public Cursor getAllDataFromReplacementGrowthRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_REPLACEMENT_GROWTH_RECORDS, null);

        return res;
    }
    public Cursor getAllDataFromPhenoMorphoRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PHENO_MORPHO_VALUES, null);

        return res;
    }
    public Cursor getDataFromPhenoMorphoValuesWhere(String sex, String tag, String pheno, String morphos, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PHENO_MORPHO_VALUES+ " where gender is ? and tag is ? and phenotypic is ? and morphometric is ? and date_collected is ?", new String[]{sex, tag, pheno, morphos, date});

        return res;
    }
    public Cursor getDataFromReplacementPhenoMorphosWhere(Integer inv_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select values_id from " +TABLE_PHENO_MORPHOS+" where replacement_inventory is ?", new String[]{inv_id.toString()});

        return res;
    }
    public Cursor getIDFromReplacementInventoyWhereTag(String tag){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID from " +TABLE_REPLACEMENT_INVENTORIES+" where REPLACEMENT_INV_REPLACEMENT_TAG is ?", new String[]{tag});

        return res;
    }
    public Cursor getDataFromPhenoMorphoValuesWhereValuesID(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PHENO_MORPHO_VALUES+" where id is ?", new String[]{id.toString()});

        return res;
    }

    /*pheno_sex, pheno_tag, pheno_record, morphos, pheno_date, null);*/

/*    public static final String TABLE_PHENO_MORPHO_VALUES = "pheno_morpho_values";
    public static final String PHENO_MORPHO_VALUES_COL_0 = "id";
    public static final String PHENO_MORPHO_VALUES_COL_1   = "gender";
    public static final String PHENO_MORPHO_VALUES_COL_2   = "tag";
    public static final String PHENO_MORPHO_VALUES_COL_3   = "phenotypic";
    public static final String PHENO_MORPHO_VALUES_COL_4   = "morphometric";
    public static final String PHENO_MORPHO_VALUES_COL_5   = "date_collected";
    public static final String PHENO_MORPHO_VALUES_COL_6   = "deleted_at";
*/










    public Cursor getIDFromBreedersWhere(String generation, String line, String family){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BREEDER+ " where BREEDER_FAMILY is "+family+ " and BREEDER_LINE is "+line+" and BREEDER_GENERATION is "+generation, null);
        return res;
    }
    public Cursor getBreedersFromPen(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PEN+ " where PEN_TYPE LIKE '%Layer%'", null);
        return res;
    }
    public Cursor getAllDataFromBreeders(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BREEDER, null);

        return res;
    }
    public Cursor getDataFromBreederInvWhereTag(String tag){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BREEDER_INVENTORIES+ " where BREEDER_INV_BREEDER_TAG like ?", new String[]{tag}, null);

        return res;
    }
    public Cursor getBreederPhenoRecordwhereID(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BREEDER_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS+ " where BREEDER_PHENO_MORPHO_INVENTORY_ID like ?", new String[] {id.toString()}, null);

        return res;
    }
    public Cursor getAllDataFromBreederInventory(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BREEDER_INVENTORIES, null);

        return res;
    }
    public Cursor getAllDataFromBreederFeedingRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BREEDER_FEEDING_RECORDS, null);

        return res;
    }
    public Cursor getAllDataFromBreederPhenoMorphoRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BREEDER_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS, null);

        return res;
    }

    public Cursor getAllDataFromBreederGrowthRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BREEDER_GROWTH_RECORDS, null);

        return res;
    }
    public Cursor getAllDataFromPhenoMorphoRecordsBreeder(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_BREEDER_PHENOTYPIC_AND_MORPHOMETRIC_RECORDS, null);

        return res;
    }

    public Cursor getAllDataFromEggProduction(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_EGG_PRODUCTION, null);

        return res;
    }
    public Cursor getAllDataFromEggQuality(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_EGG_QUALITY, null);

        return res;
    }

    public Cursor getAllDataFromHatcheryRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_HATCHERY_RECORD, null);

        return res;
    }

    public Cursor getAllDataFromMortandSalesRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_MORTALITY_AND_SALES, null);

        return res;
    }
    public Cursor getIDFromBreederInventoyWhereTag(String tag){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID from " +TABLE_BREEDER_INVENTORIES+" where BREEDER_INV_BREEDER_TAG is ?", new String[]{tag});

        return res;
    }
    public Cursor getDataFromBreederPhenoMorphosWhere(Integer inv_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select values_id from " +TABLE_PHENO_MORPHOS+" where breeder_inventory is ?", new String[]{inv_id.toString()});

        return res;
    }




















    public List<String> getAllDataFromGenerationasList(){
        List<String> generations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT GENERATION_NUMBER FROM " + TABLE_GENERATION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                generations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return generations;
    }
    public List<String> getAllDataFromLineasList(String selected_generation){
        List<String> lines = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT LINE_NUMBER FROM " + TABLE_LINE + " WHERE LINE_GENERATION = " + selected_generation;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                lines.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return lines;
    }
    public List<String> getAllDataFromFamilyasList(String selected_line, String selected_generation){
        List<String> lines = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT FAMILY_NUMBER FROM " + TABLE_FAMILY + " WHERE FAMILY_LINE = " + selected_line + " AND FAMILY_GENERATION = " + selected_generation;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                lines.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return lines;
    }
    public List<String> getAllDataFromPenasList(){
        String pen = "Layer";
        List<String> generations = new ArrayList<String>();

        /*SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PEN+ " where PEN_TYPE LIKE '%Layer%'", null);
        return res;*/

        String selectQuery = ("select * from pen_table where PEN_TYPE LIKE '%Layer%'");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                generations.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return generations;
    }
    public List<String> getAllDataFromBrooderPenasList(){
        String pen = "Layer";
        List<String> generations = new ArrayList<String>();

        /*SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PEN+ " where PEN_TYPE LIKE '%Layer%'", null);
        return res;*/

        String selectQuery = ("select * from pen_table where PEN_TYPE LIKE '%Brooder%'");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                generations.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return generations;
    }
    public boolean updatePen(String number, String type, Integer current, Integer total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PEN_COL_1, number);
        contentValues.put(PEN_COL_2, type);
        contentValues.put(PEN_COL_3, current);
        contentValues.put(PEN_COL_4, total);
        long result = db.update(TABLE_PEN,contentValues,"PEN_NUMBER = ?", new String[]{number});
        if (result == -1)
            return  false;
        else
            return true;

        /*        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PEN_COL_1, pen_number);
        contentValues.put(PEN_COL_2, pen_type);
        contentValues.put(PEN_COL_3, pen_current_capacity);
        contentValues.put(PEN_COL_4, pen_total_capacity);
        long result = db.insert(TABLE_PEN,null,contentValues);
        if (result == -1)
            return  false;
        else
            return true;*/

    }
    public boolean updateDataAddress(String id, String breed, String region, String province, String town, String barangay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, breed);
        contentValues.put(COL_3, region);
        contentValues.put(COL_4, province);
        contentValues.put(COL_5, town);
        contentValues.put(COL_6, barangay);
        db.update(TABLE_PROFILE, contentValues, "ID = ?", new String[]{ id });
        return true;
    }

    public boolean updateDataContacts(String id, String phone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_7, phone);
        contentValues.put(COL_8, email);
        db.update(TABLE_PROFILE, contentValues, "ID = ?", new String[]{ id });
        return true;
    }
    private Line cursorToLine(Cursor cursor) {
        Line line = new Line();
        line.setGeneration_number(cursor.getString(0));
        line.setLine_number(cursor.getString(1));


        // get The company by id
      /*  long companyId = cursor.getLong(7);
        Generation generation = new Generation();*/
        //generation.getGeneration_number();

      /*  CompanyDAO dao = new CompanyDAO(mContext);
        Company company = dao.getCompanyById(companyId);*/
      /*  if ( generation.getGeneration_number() != null)
            line.setGeneration_number(company);
*/
        return line;
    }
}
