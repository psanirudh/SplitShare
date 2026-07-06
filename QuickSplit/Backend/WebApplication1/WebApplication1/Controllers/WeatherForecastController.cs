using Microsoft.AspNetCore.Mvc;

namespace WebApplication1.Controllers
{
    [ApiController]
    [Route("test")]
    public class WeatherForecastController : ControllerBase
    {

        [HttpGet]
        [Route("ping")]
        public string ping()
        {
            return "pong";
        }

        [HttpGet]
        [Route("pong")]
        public string pong()
        {
            return "ping";
        }
    }
}
