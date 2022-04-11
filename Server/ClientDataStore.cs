using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Server
{
    class ClientDataStore
    {
        private List<ClientHandler> clientList = new List<ClientHandler>();
        private static ClientDataStore inst = new ClientDataStore();
        private ClientDataStore()
        {
        }

        public static ClientDataStore instance
        {
            get
            {
                return inst;
            }
        }

        public void addClient(ClientHandler cl)
        {
            lock (clientList)
            {
                clientList.Add(cl);
            }
        }

        public void stopClients()
        {
            lock (clientList)
            {
                foreach (ClientHandler cl in clientList)
                    cl.stopClient();
            }
        }

        public int clientCount
        {
            get
            {
                return clientList.Count;
            }
        }
    }
}
