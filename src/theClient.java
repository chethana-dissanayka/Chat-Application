//import necessary classes 
import java.awt.Color;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


//declares a class named theClient which extends javax.swing.JFrame
public class theClient extends javax.swing.JFrame {
// OOP: An instance of a class named Source.
// username: Stores the username of the client.
// address: Stores the default server address as "localhost".
// users: An ArrayList to store the usernames of connected users
    Source OOP = new Source();
    String username, address = "192.168.93.47";
    ArrayList<String> users = new ArrayList();

//variable tracks whether the client is connected to the server.
    Boolean isConnected = false;

//variables are used for network communication with the server.
    Socket theSocket;
    BufferedReader theReader;
    PrintWriter theWriter;

// method is responsible for listening to incoming messages from the server asynchronously
    public void ListenThread() {
        Thread IncomingReader = new Thread(new Message());
        IncomingReader.start();
    }

//method adds a new user to  the list of users
    public void userAdd(String data) {
        users.add(data);
    }
//method remove a user from the user list
    public void userRemove(String data) {
        sentMessage.append(data + " is now offline.\n");
    }

    //method iterates through the list of users and performs some action for each user.
    public void writeUsers() {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for (String token : tempList) {

        }
    }

// method sends a disconnect message to the server
    public void sendDisconnect() {
        String bye = (username + ": :Disconnect");
        try {
            theWriter.println(bye);
            theWriter.flush();
        } catch (Exception e) {
            sentMessage.append("You're not connected.\n");
        }
    }

//method handles the disconnection process of the client
    public void Disconnect() {
        try {

            //closes the socket connection (theSocket) to the server.
            // appends a "Disconnected" message to the sentMessage area.
            sentMessage.append("Disconnected.\n");
            theSocket.close();
        } catch (Exception ex) {

        }

        //indicate that the client is no longer connected
        isConnected = false;
        // allowing the user to input their name for a new connection.
        client_Name.setEditable(true);

    }

// constructor method for the theClient class. It initializes the components of the GUI.
    public theClient() {
        initComponents();
    }



//used to suppress compiler warnings of unchecked operations.
    @SuppressWarnings("unchecked")

//method initializes and sets up all the graphical components of the user interfac
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(); // main container for other components.
        jScrollPane1 = new javax.swing.JScrollPane();
        sentMessage = new javax.swing.JTextArea();
        Message = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        disConnect = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        isConnnected = new javax.swing.JLabel();
        client_Name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Send = new javax.swing.JLabel();


// default close operation for the window. 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

//set application layout
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        sentMessage.setBackground(new java.awt.Color(240, 240, 240));
        sentMessage.setColumns(20);
        sentMessage.setFont(new java.awt.Font("Segoe Script", 1, 11)); // NOI18N
        sentMessage.setRows(5);
        jScrollPane1.setViewportView(sentMessage);

        Message.setFont(new java.awt.Font("Segoe Script", 1, 11)); 
        Message.setText("Your messages");
        Message.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Message.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MessageMouseEntered(evt);
            }
        });
        Message.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MessageKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MessageKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MessageKeyTyped(evt);
            }
        });


        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

// disconnect icon setup
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        disConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_disconnected_30px_1.png"))); // NOI18N
        disConnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disConnectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                disConnectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                disConnectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(disConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(disConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


//connected icon setup
        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        isConnnected.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_connect_30px.png"))); // NOI18N
        isConnnected.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                isConnnectedMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isConnnectedMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isConnnectedMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(isConnnected, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(isConnnected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


//client name space setup
        client_Name.setBackground(new java.awt.Color(240, 240, 240));
        client_Name.setFont(new java.awt.Font("Segoe Script", 1, 11)); 
        client_Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        client_Name.setText("Client name");
        client_Name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        client_Name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                client_NameMouseExited(evt);
            }
        });
        client_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                client_NameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                client_NameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                client_NameKeyTyped(evt);
            }
        });

// label configuration
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_people_30px_1.png"))); 
        jLabel4.setText(".");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        jLabel5.setText("...");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        
//sent button configuration
        Send.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Send.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_paper_plane_30px.png"))); 
        Send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SendMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SendMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SendMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Send, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Send)
        );

//whole layout configuration
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Message, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(client_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(client_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Message, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

   
//mouse click event declaration  

//connected icon mouse click event
    private void isConnnectedMouseClicked(java.awt.event.MouseEvent evt) {
        
        switch (client_Name.getText()) {
            case "":
                client_Name.setText("Input yuor name!!");
                break;
            case "Client name":
                client_Name.setText("Input yuor name!!");
                break;
            case "Input yuor name!!":
                break;
            default:
                jLabel5.setForeground(Color.GREEN);
                if (isConnected == false) {
                    username = client_Name.getText();
                    client_Name.setEditable(false);

                    try {
                        theSocket = new Socket(address, 2222);
                        InputStreamReader streamreader = new InputStreamReader(theSocket.getInputStream());
                        theReader = new BufferedReader(streamreader);
                        theWriter = new PrintWriter(theSocket.getOutputStream());
                        theWriter.println(username + ": has connected :Connect");
                        theWriter.flush();
                        isConnected = true;
                    } catch (Exception ex) {
                        sentMessage.append("Cannot Connect! Try Again. \n");
                        client_Name.setEditable(true);
                    }

                    ListenThread();

                } else if (isConnected == true) {
                    sentMessage.append("You are connected. \n");
                }
        }
    }

//disconnected icon mouse click event

    private void disConnectMouseClicked(java.awt.event.MouseEvent evt) {
        sendDisconnect();
        jLabel5.setForeground(Color.black);
        Disconnect();
    }

//sent icon click event
    private void SendMouseClicked(java.awt.event.MouseEvent evt) {

        switch (Message.getText()) {
            case "Your messages":
                Message.setText("Type your message here");
                Message.requestFocus();
                break;
            case "Client name":
                break;
            case "Input yuor name":
                break;
            default:
              try {
                theWriter.println(username + " : " + Message.getText() + ":" + "Chat");
                theWriter.flush();
            } catch (Exception ex) {
                sentMessage.append("You're not connected. \n");
            }
            Message.setText("");
            Message.requestFocus();

        }

        Message.setText("Your messages");
        Message.requestFocus();
    }


    private void MessageKeyPressed(java.awt.event.KeyEvent evt) {
        if (Message.getText().equals("Your messages"))
            Message.setText("");
    }

    private void client_NameKeyPressed(java.awt.event.KeyEvent evt) {
        if (client_Name.getText().equals("Client name"))
            client_Name.setText("");
    }

    private void isConnnectedMouseEntered(java.awt.event.MouseEvent evt) {
        OOP.changeColor(jPanel4);
    }

    private void isConnnectedMouseExited(java.awt.event.MouseEvent evt) {
        OOP.NormalWhite(jPanel4);
    }

    private void disConnectMouseEntered(java.awt.event.MouseEvent evt) {
        OOP.changeColor(jPanel3);
    }

    private void disConnectMouseExited(java.awt.event.MouseEvent evt) {
        OOP.NormalWhite(jPanel3);
    }

    private void MessageMouseEntered(java.awt.event.MouseEvent evt) {

    }

    private void client_NameMouseExited(java.awt.event.MouseEvent evt) {
    }

    private void client_NameKeyTyped(java.awt.event.KeyEvent evt) {    
        switch (client_Name.getText()) {
            case "Client name":
                client_Name.setText("");
                break;
            case "Input yuor name!!":
                client_Name.setText("");
            default:
        }
    }

    private void client_NameKeyReleased(java.awt.event.KeyEvent evt) {
        switch (client_Name.getText()) {
            case "":
                client_Name.setText("Client name");
                break;
            default:
                String name = client_Name.getText();
                String result = name.substring(0, 1).toUpperCase() + name.substring(1);
                client_Name.setText(result);
        }
    }

    private void SendMouseEntered(java.awt.event.MouseEvent evt) {
        OOP.changeColor(jPanel5);
    }

    private void SendMouseExited(java.awt.event.MouseEvent evt) {
        OOP.NormalWhite(jPanel5);
    }

    private void MessageKeyReleased(java.awt.event.KeyEvent evt) {
        switch (Message.getText()) {
            case "":
                Message.setText("Your messages");
                break;
            default:
                String name = Message.getText();
                String result = name.substring(0, 1).toUpperCase() + name.substring(1);
                Message.setText(result);
        }
    }

    private void MessageKeyTyped(java.awt.event.KeyEvent evt) {
    }

    

//entry point of the program.
//It initializes the GUI by creating an instance of the theServer class and making it visible.
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(theClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(theClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(theClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(theClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


//Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new theClient().setVisible(true);
            }
        });
    }

// Variables declaration 
    private javax.swing.JTextField Message;
    private javax.swing.JLabel Send;
    private javax.swing.JTextField client_Name;
    private javax.swing.JLabel disConnect;
    private javax.swing.JLabel isConnnected;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea sentMessage;


 public class Message implements Runnable {

        @Override
        public void run() {
            String[] data;
            String stream;

            try {
                while (!(stream = theReader.readLine()).equals(null)) {
                    data = stream.split(":");

                    switch (data[2]) {
                        case "Chat":
                            sentMessage.append(data[0] + ":" + data[1] + "\n");
                            sentMessage.setCaretPosition(sentMessage.getDocument().getLength());
                            break;
                        case "Connnect":
                            sentMessage.removeAll();
                            userAdd(data[0]);
                            break;
                        case "Disconnect":
                            userRemove(data[0]);
                            break;
                        case "Done":
                            writeUsers();
                            users.clear();
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
