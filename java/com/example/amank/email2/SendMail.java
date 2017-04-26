package com.example.amank.email2;

/**
 * Created by amank on 21-02-2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


//Class is extending AsyncTask because this class is going to perform a networking operation
public class SendMail extends AsyncTask<Void,Void,Void> {



    //Declaring Variables
    private Context context;
    private Session session;

    //Information to send email
    private String email;
    private String subject;
    private String message;
    private String message2;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public SendMail(Context context, String email, String sub, String msg, String msg2){
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = sub;
        this.message = msg;
        this.message2= msg2;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context,"Sending Mail","Please Wait For File To Attach...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context,"Message Sent",Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(Config.EMAIL));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject

            //mm.setSubject("internal");
            mm.setSubject(subject);
            //Adding message
           // mm.setText(message);




            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(addHtmlPart(message+"\nUser Comments:\n"+message2));
            //multipart.addBodyPart(addHtmlPart("internal mesage in addbodypart"));
            multipart.addBodyPart(addAttach("/storage/emulated/0/Pictures/SWACHH/IMG_1.jpg", true));
            mm.setContent(multipart);




            //Sending email
            Transport.send(mm);
            Log.e("ok","ok");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public BodyPart addHtmlPart(String html) throws MessagingException
    {
        BodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(html,"text/html");
        htmlPart.setDisposition(BodyPart.INLINE);
        return htmlPart;
    }

    public BodyPart addAttach(String fileName, boolean isInlineImage) throws MessagingException
    {
        BodyPart attach = new MimeBodyPart();
        DataSource source = new FileDataSource(fileName);
        attach.setDataHandler(new DataHandler(source));
        attach.setFileName(fileName);

        if(isInlineImage)
            attach.setHeader("Content-ID","<image_" + fileName + ">");

        return attach;
    }




}



