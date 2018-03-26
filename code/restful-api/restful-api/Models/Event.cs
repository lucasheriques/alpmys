using System;

namespace restfulapi.Models
{
    public class Event
    {
        public long Id { get; set; }
        public String Name { get; set; }
        public String Description { get; set; }
        public long UserId { get; set; }
        public User User { get; set; }
    }
}
