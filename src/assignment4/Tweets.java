package assignment4;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object representation of Tweets
 * You should not need to modify this class
 * If you feel like you do so, ask on piazza before proceeding
 */

public class Tweets
    {
        private int Id;
        private String Name;
        private String Date;
        private String Text;
        
        
        //setters
        public void setId(int Id) { this.Id = Id; }

        public void setDate(String Date) { this.Date = Date; }

        public void setText(String Text) { this.Text = Text; }

        public void setName(String Name) { this.Name = Name; }

        
        //getters
        public String getDate() { return this.Date; }

        public String getName() { return this.Name; }

        public int getId() { return this.Id; }

        public String getText() { return this.Text; }

       

        public Tweets(){}

        
        @Override public String toString() {
            return "(" + this.getId()
                    + " " + this.getName().toString()
                    + " " + this.getDate()
                    + ") " + this.getText();
        }
        
    }


