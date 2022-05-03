package com.ahmadabuhasan.pointofsale.database;

import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;

import com.ahmadabuhasan.pointofsale.Constant;
import com.ahmadabuhasan.pointofsale.R;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import es.dmoral.toasty.Toasty;

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "POS.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // https://github.com/prof18/Database-Backup-Restore.git
    public void backup(String outFileName) {

        //database path
        final String inFileName = context.getDatabasePath(DATABASE_NAME).toString();

        try {
            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            //
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            // Close the streams
            output.flush();
            output.close();
            fis.close();

            Toasty.success(context, R.string.backup_completed_successfully, Toasty.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toasty.error(context, R.string.unable_to_backup_database_retry, Toasty.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void importDB(String inFileName) {

        final String outFileName = context.getDatabasePath(DATABASE_NAME).toString();

        try {
            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            // Transfer bytes from the input file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Close the streams
            output.flush();
            output.close();
            fis.close();

            Toasty.success(context, R.string.database_Import_completed, Toasty.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toasty.error(context, R.string.unable_to_import_database_retry, Toasty.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Create Table
    //customers
    private static final String CREATE_CUSTOMERS =
            "CREATE TABLE " + Constant.customers + "("
                    + Constant.CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.CUSTOMER_NAME + " TEXT,"
                    + Constant.CUSTOMER_CELL + " TEXT,"
                    + Constant.CUSTOMER_EMAIL + " TEXT,"
                    + Constant.CUSTOMER_ADDRESS + " TEXT"
                    + ")";

    //expense
    private static final String CREATE_EXPENSE =
            "CREATE TABLE " + Constant.expense + "("
                    + Constant.EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.EXPENSE_NAME + " TEXT,"
                    + Constant.EXPENSE_NOTE + " TEXT,"
                    + Constant.EXPENSE_AMOUNT + " TEXT,"
                    + Constant.EXPENSE_DATE + " TEXT,"
                    + Constant.EXPENSE_TIME + " TEXT"
                    + ")";

    //order_details
    private static final String CREATE_ORDER_DETAILS =
            "CREATE TABLE " + Constant.orderDetails + "("
                    + Constant.ORDER_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.INVOICE_ID + " TEXT,"
                    + Constant.PRODUCT_NAME + " TEXT,"
                    + Constant.PRODUCT_WEIGHT + " TEXT,"
                    + Constant.PRODUCT_QTY + " TEXT,"
                    + Constant.PRODUCT_PRICE + " TEXT,"
                    + Constant.PRODUCT_IMAGE + " TEXT,"
                    + Constant.PRODUCT_ORDER_DATE + " TEXT,"
                    + Constant.ORDER_STATUS + " TEXT"
                    + ")";

    //order_list
    private static final String CREATE_ORDER_LIST =
            "CREATE TABLE " + Constant.orderList + "("
                    + Constant.ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.INVOICE_ID + " TEXT,"
                    + Constant.ORDER_DATE + " TEXT,"
                    + Constant.ORDER_TIME + " TEXT,"
                    + Constant.ORDER_TYPE + " TEXT,"
                    + Constant.ORDER_PAYMENT_METHOD + " TEXT,"
                    + Constant.CUSTOMER_NAME + " TEXT,"
                    + Constant.TAX + " TEXT,"
                    + Constant.DISCOUNT + " TEXT,"
                    + Constant.ORDER_STATUS + " TEXT"
                    + ")";

    //order_type
    private static final String CREATE_ORDER_TYPE =
            "CREATE TABLE " + Constant.ORDER_TYPE + "("
                    + Constant.ORDER_TYPE_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.ORDER_TYPE_NAME + "TEXT"
                    + ")";

    //payment_method
    private static final String CREATE_PAYMENT_METHOD =
            "CREATE TABLE " + Constant.paymentMethod + "("
                    + Constant.PAYMENT_METHOD_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.PAYMENT_METHOD_NAME + "TEXT"
                    + ")";

    //product_cart
    private static final String CREATE_PRODUCT_CART =
            "CREATE TABLE " + Constant.productCart + "("
                    + Constant.PRODUCT_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.PRODUCT_WEIGHT + "TEXT,"
                    + Constant.PRODUCT_WEIGHT_UNIT + "TEXT,"
                    + Constant.PRODUCT_PRICE + "TEXT,"
                    + Constant.PRODUCT_QTY + "INTEGER,"
                    + Constant.STOCK + "TEXT"
                    + ")";

    //product_category
    private static final String CREATE_PRODUCT_CATEGORY =
            "CREATE TABLE " + Constant.productCategory + "("
                    + Constant.CATEGORY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.CATEGORY_NAME + "TEXT"
                    + ")";

    //product_weight
    private static final String CREATE_PRODUCT_WEIGHT =
            "CREATE TABLE " + Constant.PRODUCT_WEIGHT + "("
                    + Constant.WEIGHT_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.WEIGHT_UNIT + "TEXT"
                    + ")";

    //product
    private static final String CREATE_PRODUCT =
            "CREATE TABLE " + Constant.products + "("
                    + Constant.PRODUCT_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.PRODUCT_NAME + "TEXT,"
                    + Constant.PRODUCT_CODE + "TEXT,"
                    + Constant.PRODUCT_CATEGORY + "TEXT,"
                    + Constant.PRODUCT_DESCRIPTION + "TEXT,"
                    + Constant.PRODUCT_BUY_PRICE + "TEXT,"
                    + Constant.PRODUCT_SELL_PRICE + "TEXT,"
                    + Constant.PRODUCT_SUPPLIER + "TEXT,"
                    + Constant.PRODUCT_IMAGE + "TEXT,"
                    + Constant.PRODUCT_STOCK + "TEXT,"
                    + Constant.PRODUCT_WEIGHT_UNIT_ID + "TEXT,"
                    + Constant.PRODUCT_WEIGHT + "TEXT"
                    + ")";

    //shop
    private static final String CREATE_SHOP =
            "CREATE TABLE " + Constant.SHOP + "("
                    + Constant.SHOP_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.SHOP_NAME + "TEXT,"
                    + Constant.SHOP_CONTACT + "TEXT,"
                    + Constant.SHOP_EMAIL + "TEXT,"
                    + Constant.SHOP_ADDRESS + "TEXT,"
                    + Constant.SHOP_CURRENCY + "TEXT,"
                    + Constant.TAX + "TEXT"
                    + ")";

    //suppliers
    private static final String CREATE_SUPPLIERS =
            "CREATE TABLE " + Constant.suppliers + "("
                    + Constant.SUPPLIERS_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.SUPPLIERS_NAME + "TEXT,"
                    + Constant.SUPPLIERS_CONTACT_PERSON + "TEXT,"
                    + Constant.SUPPLIERS_CELL + "TEXT,"
                    + Constant.SUPPLIERS_EMAIL + "TEXT,"
                    + Constant.SUPPLIERS_ADDRESS + "TEXT"
                    + ")";

    // Creating Table
    /*@Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMERS);
        db.execSQL(CREATE_EXPENSE);
        db.execSQL(CREATE_ORDER_DETAILS);
        db.execSQL(CREATE_ORDER_LIST);
        db.execSQL(CREATE_ORDER_TYPE);
        db.execSQL(CREATE_PAYMENT_METHOD);
        db.execSQL(CREATE_PRODUCT_CART);
        db.execSQL(CREATE_PRODUCT_CATEGORY);
        db.execSQL(CREATE_PRODUCT_WEIGHT);
        db.execSQL(CREATE_PRODUCT);
        db.execSQL(CREATE_SHOP);
        db.execSQL(CREATE_SUPPLIERS);
    }*/

    // Upgrading Database
    /*@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constant.customers);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.expense);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.orderDetails);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.orderList);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.ORDER_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.paymentMethod);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.productCart);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.productCategory);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.PRODUCT_WEIGHT);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.products);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.SHOP);
        db.execSQL("DROP TABLE IF EXISTS " + Constant.suppliers);

        onCreate(db);
    }*/
}