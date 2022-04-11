using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using System.Data.SQLite;

using System.IO;

namespace Server
{
    class ClientHandler
    {
        private Socket socket = null;
        private int _idx = -1;
        private Thread thread = null;
        private bool shouldRun = true;
        private bool isRunning = true;

        private SQLiteConnection conn = null;

        public ClientHandler(Socket sk, int id)
        {
            socket = sk;
            _idx = id;
        }

        public void initClient()
        {
            if (thread != null)
                return;

            thread = new Thread(new ThreadStart(run));
            thread.Start();
        }

        public void stopClient()
        {
            if (thread == null)
                return;

            socket.Close();
            shouldRun = false;
        }

        public bool SocketConnected(Socket s)
        {
            bool part1 = s.Poll(10000, SelectMode.SelectRead);
            bool part2 = (s.Available == 0);
            if (part1 && part2)
                return false;
            else
                return true;
        }

        //
        // message handler
        //

        private void handleMsg(String msg)
        {
            //prelucrare msg.....
            char[] sep = { '-', ' ' };
            string[] arrMsg = msg.Split(sep, StringSplitOptions.RemoveEmptyEntries);

            string cloudPath = "E:\\Cloud";

            if (arrMsg[0].StartsWith(Messages.sLoginReq))
            {
                //login request
                Console.WriteLine("Login Request");

                conn = SQLiteHandler.ConnectToDb();
                if (SQLiteHandler.CheckUserCreditentials(conn, arrMsg[1], arrMsg[2]))
                {
                    sendResponse("login-ok");
                }
                else
                {
                    sendResponse("login-inv");
                }

                Console.WriteLine("Response sent");

                SQLiteHandler.DisconnectFromDb(conn);
                conn = null;

                return;
            }

            else if (arrMsg[0].StartsWith(Messages.sRegisterReq))
            {
                //register request
                Console.WriteLine("Register Request");

                bool ok = false;

                conn = SQLiteHandler.ConnectToDb();
                if (!SQLiteHandler.CheckUser(conn, arrMsg[1]))
                {
                    ok = true;
                    sendResponse("regis-ok");
                }
                else
                {
                    ok = false;
                    sendResponse("regis-inv");
                }

                if(ok)
                {
                    SQLiteHandler.InsertUser(conn, arrMsg[1], arrMsg[2]);
                    Directory.CreateDirectory(cloudPath + "\\" + arrMsg[1]);
                }

                Console.WriteLine("Response sent");

                SQLiteHandler.DisconnectFromDb(conn);
                conn = null;

                return;
            }
            else if (arrMsg[0].StartsWith(Messages.sFolderReq))
            {
                //get folder content
                int i = 0;
                DirectoryInfo folder = new DirectoryInfo(@cloudPath + "\\" + arrMsg[1]);

                foreach (var file in folder.GetFiles("*.mp3"))
                {
                    sendResponse(file.Name);
                    //send author
                    sendResponse(i.ToString());
                    ++i;
                }
                sendResponse("end");
            }
            else if (arrMsg[0].StartsWith(Messages.sDownloadReq))
            {
                //download file
                int i = 0;
                bool found = false;
                DirectoryInfo folder = new DirectoryInfo(@cloudPath + "\\" + arrMsg[1]);

                foreach (var file in folder.GetFiles("*.mp3"))
                {
                    if(i == int.Parse(arrMsg[2]))
                    {
                        found = true;
                        socket.SendFile(file.FullName);
                        break;
                    }
                }
                if (!found)
                {
                    sendResponse("end");
                }
            }
            else if (arrMsg[0].StartsWith(Messages.sUploadReq))
            {
                //upload file
            }
            else
            {
                // wrong request
                sendResponse(Messages.sError);
            }
        }

        //
        // responses
        //

        private void sendResponse(String msg)
        {
            byte[] bytesMsgRaspuns = Encoding.ASCII.GetBytes(msg);
            socket.Send(bytesMsgRaspuns);
        }

        private void run()
        {
            // Attention! This is the largest message one can receive!
            

            while (shouldRun)
            {
                //Console.WriteLine("Client... "+_idx);
                byte[] rawMsg = new byte[256];
                try
                {
                    int bCount = socket.Receive(rawMsg);
                    string msg = Encoding.UTF8.GetString(rawMsg);
                    if (bCount > 0)
                        Console.WriteLine("Client " + _idx + ": " + msg);
                    handleMsg(msg); 
                }
                catch (Exception ex)
                {
                    //Console.WriteLine("Client exxxccp ");
                    return;
                }
                Thread.Sleep(1);
            }
            isRunning = false;
            
        }

        public bool isAlive()
        {
            return isRunning;
        }
    }
}
