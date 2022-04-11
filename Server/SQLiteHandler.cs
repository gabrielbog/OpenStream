using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Data.SQLite;

namespace Server
{
    internal class SQLiteHandler
    {
        public static SQLiteConnection ConnectToDb()
        {
            SQLiteConnection conn = null;
            conn = new SQLiteConnection("Data Source=userdb.sqlite; Version=3; New=False;");

            try
            {
                conn.Open();
                Console.WriteLine("Connected to DB successfully!");
            }
            catch (Exception ex)
            {
                //Console.WriteLine("ERROR: " + ex.Message);
                conn.Close();
            }

            return conn;
        }

        public static void DisconnectFromDb(SQLiteConnection conn)
        {
            if (conn != null && conn.State == System.Data.ConnectionState.Open)
            {
                conn.Close();
                Console.WriteLine("Disconnected from DB successfully!");
            }
        }

        public static void InsertUser(SQLiteConnection conn, string username, string password)
        {
            if (conn != null && conn.State == System.Data.ConnectionState.Open)
            {
                SQLiteCommand cmd = conn.CreateCommand();
                cmd.CommandText = "INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "');";
                try
                {
                    cmd.ExecuteNonQuery();
                    Console.WriteLine("Creditentials Added!");
                }
                catch(Exception ex)
                {
                    Console.WriteLine("ERROR: " + ex.ToString());
                }
            }
            else
            {
                Console.WriteLine("Please connect first!");
            }
        }

        public static void RemoveUser(SQLiteConnection conn, string username)
        {
            if (conn != null && conn.State == System.Data.ConnectionState.Open)
            {
                SQLiteCommand cmd = conn.CreateCommand();
                cmd.CommandText = "DELETE FROM users WHERE username='" + username + "';";
                try
                {
                    cmd.ExecuteNonQuery();
                    Console.WriteLine("Creditentials Removed!");
                }
                catch (Exception ex)
                {
                    Console.WriteLine("ERROR: " + ex.ToString());
                }
            }
            else
            {
                Console.WriteLine("Please connect first!");
            }
        }

        public static bool CheckUser(SQLiteConnection conn, string username)
        {
            if (conn != null && conn.State == System.Data.ConnectionState.Open)
            {
                SQLiteDataReader reader = null;
                SQLiteCommand cmd = conn.CreateCommand();
                cmd.CommandText = "SELECT username FROM users WHERE username='" + username + "';";

                try
                {
                    reader = cmd.ExecuteReader();

                    if (reader.FieldCount != 0)
                    {
                        while (reader.Read())
                        {
                            if(reader.GetString(0) == username)
                            {
                                Console.WriteLine("User exists");
                                return true;
                            }
                        }
                        Console.WriteLine("User does not exist");
                        return false;
                    }
                    else
                    {
                        Console.WriteLine("Empty Database");
                        return false;
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine("ERROR: " + ex.ToString());
                }
            }
            else
            {
                Console.WriteLine("Please connect first!");
            }
            return false;
        }

        public static bool CheckUserCreditentials(SQLiteConnection conn, string username, string password)
        {
            if (conn != null && conn.State == System.Data.ConnectionState.Open)
            {
                SQLiteDataReader reader = null;
                SQLiteCommand cmd = conn.CreateCommand();
                cmd.CommandText = "SELECT username, password FROM users WHERE username='" + username + "';";

                try
                {
                    reader = cmd.ExecuteReader();

                    if (reader.FieldCount != 0)
                    {
                        while (reader.Read())
                        {
                            Console.WriteLine(reader.GetString(0) + " " + reader.GetString(1));
                            if (reader.GetString(0) == username && reader.GetString(1) == password)
                            {
                                Console.WriteLine("Correct creditentials");
                                return true;
                            }
                        }
                        Console.WriteLine("Incorrect creditentials");
                        return false;
                    }
                    else
                    {
                        Console.WriteLine("Empty Database");
                        return false;
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine("ERROR: " + ex.ToString());
                }
            }
            else
            {
                Console.WriteLine("Please connect first!");
            }
            return false;
        }
    }
}
