using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Net;
using System.Net.Sockets;

namespace Server
{
    class ServerCore : ServerSocket
    {
        private Thread thread = null;
        private bool shouldRun = true;

        public void createServer(int port)
        {
            createSocket(port);
            thread = new Thread(new ThreadStart(run));
            thread.Start();
        }
        public void stopServer()
        {
            closeSocket();
        }

        private void run()
        {
            while (shouldRun)
            {
                try
                {
                    Socket socket = acceptConnection();
                    if (socket == null)
                        return;

                    Console.WriteLine("Accepted connection from: " + socket.RemoteEndPoint);
                    ClientHandler cl = new ClientHandler(socket, ClientDataStore.instance.clientCount);
                    cl.initClient();
                    ClientDataStore.instance.addClient(cl);
                }
                catch (Exception ex)
                {
                    return;
                }

                Thread.Yield();
            }
        }
    }
}
