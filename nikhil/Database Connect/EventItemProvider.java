package com.nikhil.techfest.provider;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nikhil.techfest.R;
import com.nikhil.techfest.adapter.EventItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventItemProvider {

    private static final int TECH = 0;
    private static final int NONTECH = 1;
    private static final int ROBO = 2;
    private static final int ENTRE = 3;
    private static final int ATV = 4;
    private static final int WORKSHOP = 5;
    private static final int OTHERS = 6;

    private int index;
    private String image;
    private String eventName;
    private String about;
    private String rules;
    private String loc;
    private String contact;
    private String map;

    public EventItemProvider(String image, String eventName, String about, String rules, String loc, String contact, String map) {
        this.image = image;
        this.eventName = eventName;
        this.about = about;
        this.rules = rules;
        this.loc = loc;
        this.contact = contact;
        this.map = map;
    }

    public EventItemProvider(String image, String eventName, String about, String rules, String loc, String contact) {
        this.image = image;
        this.eventName = eventName;
        this.about = about;
        this.rules = rules;
        this.loc = loc;
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public String getEventName() {
        return eventName;
    }

    public String getAbout() {
        return about;
    }

    public String getRules() {
        return rules;
    }

    public String getLoc() {
        return loc;
    }

    public String getContact() {
        return contact;
    }

    public String getMap() {
        return map;
    }

    public static void getFromDatabase(final View view, final int index) {

        final ProgressDialog dialog = new ProgressDialog(view.getContext());
        dialog.setMessage("Fetching Data, Please Wait...");
        dialog.setCancelable(false);
        dialog.show();

            String url;

            if(index == TECH) {
                url = "https://www.gtuctf19.com/android/tech.php";
            } else if(index == NONTECH) {
                url = "https://www.gtuctf19.com/android/nonTech.php";
            } else if(index == ROBO) {
                url = "https://www.gtuctf19.com/android/robotics.php";
            } else if(index == ENTRE) {
                url = "https://www.gtuctf19.com/android/entresum.php";
            } else if(index == ATV) {
                url = "https://www.gtuctf19.com/android/atv.php";
            } else if(index == WORKSHOP){
                url = "https://www.gtuctf19.com/android/workshop.php";
            } else {
                final ArrayList<EventItemProvider> itemList = new ArrayList<>();
                String[] namestr = {
                        "Vishwayantra 2.0",
                        "Ace D Expert 2.0",
                        "Conclave 2.0",
                        "Run",
                        "Blood Donation",
                        "Eye Checkup",
                        "Voting Awareness",
                        "Traffic Control And Awareness"};
                String[] aboutstr = {
                        "The competition directly encourages the development of artificial intelligence, mechatronics and robotic technology as an engineering discipline.Also,it aims to cultivate innovation and creativity among future engineers.The contest aims to create friendship among young people with similar interests, as well as help the advancement of engineering and technology in the field of robotics.This event consists of a number of tasks, to be completed by one robot.To build the robot, students require a rich knowledge in programming, mechanical design and electronic circuit design.<br><br>Objective<br><br>We would like to grab the opportunity for hosting VishwaYantra2.0 event under GTU Central Techfest 2019. By hosting this type of event in GTU Central Techfest 2019 we would be creating a platform to showcase the talent of the students in the field of robotics. It also begets a stepping stone for the other students to take inspiration from the teams.",
                        "Acting as the Ace for the students, our invited experts will guide the innovators through the path of success. Get a chance to experience the logical approach of our experts. They will share experiences, insights and opinions on the technological innovations driving modern businesses. This event will be the proud mixture of technological views along with motivational ideas tapping on wandering minds. This event will help you to focus and plan your vision to get a successful result. Learning with fun being our motto, prepare yourself for some amazing 'talks', packed with entertainment and wisdom. Come, Witness the diffusion of idealogies and technologies!",
                        "The CONCLAVE at GTU CENTRAL TECHFEST 2019 aims at spreading ideas behind the innovations. Theme of CONCLAVE this year is “TRISHOOL: INNOVATING DEFENCE, ATOMIC & SPACE TECHNOLOGY”. It provides platform for the experts, students and tech - enthusiasts to share their perspectives on the given topic. Further it aims to spread knowledge and incubate innovation and creativity for the new generation, and motivate them to take up challenging career towards development of a new INDIA.<br><br>Objectives<br><br>The CONCLAVE will be featuring a spectrum of visionary experts working towards development of our country in three main sectors: The Atomic Energy Sector, Space Technology and Defense Sector. The talks will emphasize mainly on the current research and development and the possible future outcomes in these areas and its power to create infinite possibilities in the field of technology to improve the lives of billions of people around the world. Also, experts will be sharing their experience and knowledge acquired in their respective journeys towards visionary new INDIA to an aspiring galaxy of audience over the course of three and half hours.<br>All the revenue generated by the CONCLAVE shall be donated to Police Welfare Funds (Central Armed Police Forces) of BSF, CRPF, CISF, ITBP and SSB.<br>National level CONCLAVE certificate and kit will be provided to all the participants.",
                        "Get a hint and don't leave a footprint with keeping this in mind, we are ready to set our foot for a cause. This year Vishwakarma Government Engineering College under GTU Central Techfest'19 is organising a run for green environment. This run will cover a track of 7 km, starting from GTU following a path that includes New CG Road, Visat and ends back at VGEC A-Block. Follow our steps and become a part of our social initiative for a better , sustainable future.<br><br>" + "Event Date : 30th March.",
                        "The gift of blood is the gift of life. Every year our nation requires about 5 Crore units of blood, out of which only a meager 2.5 Crore units of blood are available. At the Blood Donation Camp in the Central Techfest, we provide the opportunity to every youngster to donate his bit to the humanity. “Every drop counts.. “ , make your drop count.",
                        "Vision is a part of our major 5 senses. Thousands of people are suffering from eye problem. If diagnosed at a preventive stage, we can save ourselves from chronic ailments. Hence, it is important for us to have a comprehensive check-up on regular intervals. Keeping the vitality of the situation in mind, the eye checkup organized at our techfest will be equipped with professional equipments, specialized doctors and professional prescriptions.",
                        "Standing at the brink of general elections, we are in a crucial phase when our country needs its youth. So, targeting all the newly eligible voters out there, we are going to run an awareness campaign. Nukkad Naatak is an artistic expression of a theme through the means of acting. Hence, in the colossal event of Central Techfest’19, we aim to aware the young minds regarding importance of this responsibility called voting throught the medium of Nukkad Naatak. With an effort to cover as many colleges as possible, throughout the city, our sole motto would be to cast such an impression on viewers that encourages them to vote in the upcoming elections.",
                        "Megacities with a dense population such as ours requires a disciplined structure on the roads. Thus, with a noble cause in consideration, students of Vishwakarma Government Engineering College have come forward in an attempt to solve escalating issue of traffic, in their own effective way. This initiative aims to make people aware of the importance of abiding to a legal code of conduct while driving on streets. We will manage the traffic of most densely populated areas like RTO Circle' , ' Akhbar Nagar', 'Shastri Nagar', 'Kalupur' etc. While it may seem small, the ripple effects of these activities are extraordinary. We truly believe that the real strength lies in such small things and we hope that our small initiative will encourage others to move towards the path of societal betterment."};
                String[] rulesstr = {
                        "1. TASK : <br>" +
                                "<br>" +
                                "1.1. Before game starts, Robot will be placed in Starting Zone (SZ). <br>" +
                                "1.2. As the game starts, The Robot has to travel from Starting Zone to Pick up Zone(PZ) to pick the ball and carry it with itself. <br>" +
                                "1.3. Robot has to follow a path to reach the First Shooting Point (SP1) and place the ball down on the field. <br>" +
                                "1.4. Robot then has to hit the ball once, such that it passes through maximum rings. <br>" +
                                "1.5. After hitting one ball robot has to follow the given path which lead to the PZ. <br>" +
                                "1.6. Robot has to follow the same procedure again but, this time robot has to hit ball from SP2 and further with same procedure from SP3. <br>" +
                                "1.7. After hitting three balls robot has to come back to SZ by following the path. <br>" +
                                "1.8. When this is achieved the game will be completed. <br>" +
                                "<br>" +
                                "2. DIMENSIONS : <br>" +
                                "<br>" +
                                "2.1. Robot should fit in box of 600mm x 600mm x 600mm (lxbxh). <br>" +
                                "2.2. If team is preparing their own rack it should be in range of 500mm x 500mm x 700mm (lxbxh). <br>" +
                                "2.3. NIVIA soft tennis balls will be provided. <br>" +
                                "<br>" +
                                "3. RULES : <br>" +
                                "<br>" +
                                "3.1. Robot should be totally autonomous. <br>" +
                                "3.2. If robot drops the ball in between it will be considered as foul and robot has to start from the PZ again. <br>" +
                                "3.3. A maximum time of 3 minutes will be allotted to the team. <br>" +
                                "3.4. The Robot should have an on-board power supply. <br>" +
                                "3.5. Robots must not split into parts during the game. <br>" +
                                "3.6. The supply voltage should not exceed 12 V. <br>" +
                                "3.7. Team members are not allowed to touch their Robot without getting referee’s permission during a retry. <br>" +
                                "3.8. A team may ask for as many retries as necessary. <br>" +
                                "3.9. Robot can pick up only a single ball at a time. <br>" +
                                "3.10. Robot can not drag the ball. <br>" +
                                "3.11. Ball can be used once only. <br>" +
                                "3.12. Maximum five balls will be provided. <br>" +
                                "<br>" +
                                "4. VIOLATIONS : <br>" +
                                "<br>" +
                                "4.1. A team member touches the Robot without referee’s permission. <br>" +
                                "4.2. A team makes a false start. <br>" +
                                "4.3. Any other acts deemed to be an infringement on the rules. <br>" +
                                "4.4. The dimensions of the Robot do not satisfy the given criteria. <br>" +
                                "4.5. Robot split into parts during the game. <br>" +
                                "<br>" +
                                "5. IMPORTANT NOTES : <br>" +
                                "<br>" +
                                "5.1. Team can make their own rack for ball as they want in given dimensions(optional). <br>" +
                                "5.2. Rack provided from here will be holding ball at height of 25cm(most bottom point of ball). Team has to take ball from horizontal sides as rack may hold ball from top and bottom. <br>" +
                                "5.3. Details dimensions of the arena will be provided later. <br>" +
                                "5.4. Line colour will be black. <br>" +
                                "5.5. Team can have maximum 15 and minimum 4 participants.",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-"
                };

                String locstr[] = {
                        "IIT shed 2",
                        "L-Block Lawn Area",
                        "A-Block Auditorium",
                        "D-Block parking",
                        "M-Block",
                        "M-Block",
                        "Ahmedabad",
                        "Ahmedabad"
                };

                String contactstr[] = {
                        "Meghal Shah: 8511881133",
                        "Raj Gandhi: 9409291687",
                        "Alok Mazumder: 7043454680",
                        "Vatsal Golakiya: 9662127284",
                        "Akhil Alexander: 8141889147",
                        "Akhil Alexander: 8141889147",
                        "Akhil Alexander: 8141889147",
                        "Vatsal Golakiya: 9662127284"
                };

                String mapstr[] = {
                        "https://www.google.co.in/maps/place/23%C2%B006'25.1%22N+72%C2%B035'36.5%22E/@23.106962,72.5929285,19z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d23.1069615!4d72.5934769",
                        "https://www.google.com/maps/place/23%C2%B006'24.8%22N+72%C2%B035'44.5%22E/@23.1068865,72.5945857,18z/data=!3m1!4b1!4m13!1m6!3m5!1s0x395e83c959d4de6f:0x748d0828c02cf9fa!2sVishwakarma+Government+Engineering+College!8m2!3d23.1069404!4d72.5949187!3m5!1s0x0:0x0!7e2!8m2!3d23.1068836!4d72.5956804",
                        "https://www.google.com/maps/place/23%C2%B006'23.7%22N+72%C2%B035'44.6%22E/@23.1065885,72.5950759,18z/data=!3m1!4b1!4m13!1m6!3m5!1s0x395e83c959d4de6f:0x748d0828c02cf9fa!2sVishwakarma+Government+Engineering+College!8m2!3d23.1069404!4d72.5949187!3m5!1s0x0:0x0!7e2!8m2!3d23.1065873!4d72.5957299",
                        "https://www.google.com/maps/place/23%C2%B006'21.6%22N+72%C2%B035'41.5%22E/@23.105993,72.5943258,19z/data=!3m1!4b1!4m13!1m6!3m5!1s0x395e83c959d4de6f:0x748d0828c02cf9fa!2sVishwakarma+Government+Engineering+College!8m2!3d23.1069404!4d72.5949187!3m5!1s0x0:0x0!7e2!8m2!3d23.1059926!4d72.5948731",
                        "https://www.google.com/maps/place/Mechanical+Block/@23.1081741,72.59411,19z/data=!3m1!4b1!4m5!3m4!1s0x395e83cbaa4e4249:0xc347380ba9f59852!8m2!3d23.1081741!4d72.5946572",
                        "https://www.google.com/maps/place/Mechanical+Block/@23.1081741,72.59411,19z/data=!3m1!4b1!4m5!3m4!1s0x395e83cbaa4e4249:0xc347380ba9f59852!8m2!3d23.1081741!4d72.5946572",
                        "https://www.google.com/maps/place/Ahmedabad,+Gujarat/@23.0201818,72.4396537,11z/data=!3m1!4b1!4m5!3m4!1s0x395e848aba5bd449:0x4fcedd11614f6516!8m2!3d23.022505!4d72.5713621",
                        "https://www.google.com/maps/place/Ahmedabad,+Gujarat/@23.0201818,72.4396537,11z/data=!3m1!4b1!4m5!3m4!1s0x395e848aba5bd449:0x4fcedd11614f6516!8m2!3d23.022505!4d72.5713621"
                };

                for (int i = 0; i < namestr.length; i++) {

                    String name = namestr[i];
                    String about = aboutstr[i];
                    String rules = rulesstr[i];
                    String loc = locstr[i];
                    String contact = contactstr[i];
                    String map = mapstr[i];
                    String i_url = "images/other/";
                    i_url = i_url + name + ".jpg";

                    itemList.add(new EventItemProvider(i_url, name, about, rules, loc, contact,map));
                }

                dialog.cancel();

                RecyclerView recyclerView = view.findViewById(R.id.rv_events);
                EventItemAdapter itemAdapter = new EventItemAdapter(view.getContext(), itemList);
                recyclerView.setAdapter(itemAdapter);

                GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
                layoutManager.setOrientation(GridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                return;
            }

            final ArrayList<EventItemProvider> itemList = new ArrayList<>();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray array = new JSONArray(response);

                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject item = array.getJSONObject(i);
                                    String name = item.getString("e_name");
                                    String about = item.getString("abstract") + "<br><br>" + item.getString("description") + "<br><br>" + "Minimum Members: " + item.getString("min_members") + "<br>" + "Maximum Members: "+ item.getString("max_members");
                                    String rules = item.getString("rules");
                                    String loc = item.getString("location");
                                    String contact = item.getString("manager1") + ": " + item.getString("m_contact1") + "\n" + item.getString("manager2") + ": " + item.getString("m_contact2");
                                    String map = item.getString("maps_link");
                                    String i_url;

                                    if(index == TECH) {
                                        i_url = "images/tech/";
                                    } else if(index == NONTECH) {
                                        i_url = "images/non_tech/";
                                    } else if(index == ROBO) {
                                        i_url = "images/robotics/";
                                    } else if(index == ENTRE) {
                                        i_url = "images/entresum/";
                                    } else if(index == ATV) {
                                        i_url = "images/atv/";
                                    } else {
                                        i_url = "images/workshop/";
                                    }

                                    i_url = i_url + name + ".jpg";

                                    itemList.add(new EventItemProvider(i_url,name,about,rules,loc,contact,map));
                                }
                                dialog.cancel();
                                RecyclerView recyclerView = view.findViewById(R.id.rv_events);
                                GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
                                layoutManager.setOrientation(GridLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(layoutManager);

                                EventItemAdapter itemAdapter = new EventItemAdapter(view.getContext(), itemList);
                                LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(view.getContext(),R.anim.layout_fall_down);

                                recyclerView.setAdapter(itemAdapter);
                                recyclerView.setLayoutAnimation(controller);
//                                recyclerView.getAdapter().notifyDataSetChanged();
                                recyclerView.scheduleLayoutAnimation();
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                            } catch (JSONException e) {
                                e.printStackTrace();
                                dialog.cancel();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            dialog.cancel();
                        }
                    });

            Volley.newRequestQueue(view.getContext()).add(stringRequest);
        }
    }