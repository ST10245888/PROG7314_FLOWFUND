using System.ComponentModel.DataAnnotations;

namespace FlowFund.Api.Models
{
    //reusable across the API
    public enum LanguageCode : byte
    {
        En,
        Af,
        Zu
    }
    public class Translation
    {
        public int Id { get; set; }
        public string ContentKey { get; set; } = string.Empty;
       
        public LanguageCode LanguageCode { get; set; }
        public string TranslatedText { get; set; } = string.Empty;
    }
}
