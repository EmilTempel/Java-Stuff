package discord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ZitatMaster extends Bot {

	List<Message> zitate;

	String Urheber;
	String zitat;

	int game_len;
	ArrayList<User> teilnehmer;
	Map<User, Integer> scores;

	String last_m_id;

	public ZitatMaster(String token) throws LoginException {
		super(token);
		zitate = new ArrayList<Message>();
		setPresence(OnlineStatus.ONLINE, Activity.playing("mit deiner Mom, looool"));
		addListener(new Listener('|', new Command("random", "r") {
			public void execute(GuildMessageReceivedEvent e) {
				Guild g = e.getGuild();
				e.getChannel().sendMessage(random(g, "zitate")).queue();
			}
		}, new Command("challenge", "c") {
			public void execute(GuildMessageReceivedEvent e) {
				Guild g = e.getGuild();
				String msg = null;
				while (msg == null || msg.contains(":") || !msg.contains(" -")) {
					msg = random(g, "zitate").getContentRaw();
				}
				String[] split = msg.split(" -");
				e.getChannel().sendMessage("||" + split[0] + "||").queue();

				Urheber = split[1].split(",")[0].trim();
				zitat = msg;


			}

		}, new Command("guess", "g") {
			public void execute(GuildMessageReceivedEvent e) {
				if (Urheber != null) {
					if (cmd_body != null && cmd_body.equalsIgnoreCase(Urheber)) {
						e.getChannel().sendMessage("Das ist korrekt! Das komplette Zitat war:").queue();
						e.getChannel().sendMessage(zitat).queue();
						e.getChannel().sendMessage("^_^").queue();
						Urheber = null;
						zitat = null;

						if (game_len > 0) {
							User user = e.getAuthor();
							if (!teilnehmer.contains(user)) {
								teilnehmer.add(user);

							}

							scores.put(user, (scores.get(user) != null) ? scores.get(user) + 1 : 1);

							game_len--;

							if (game_len == 0) {
								e.getChannel()
										.sendMessage("Spiel vorbei! um den Gewinner zu erfahren: |ergebnis oder |e")
										.queue();
							} else {
								e.getChannel().sendMessage("|challenge").queue();
							}
						}
					} else {
						e.getChannel().sendMessage("Das ist leider Falsch! Versuchs doch noch mal mit: |guess oder |g")
								.queue();
					}

				} else {
					e.getChannel().sendMessage("Du musst mich erst challengen mit: |challenge oder |c").queue();

				}


			}

		}, new Command("lösung", "l", "skip") {
			public void execute(GuildMessageReceivedEvent e) {
				e.getChannel().sendMessage("Die Lösung war: " + Urheber).queue();
				Urheber = null;
				zitat = null;

				if (game_len > 0) {
					e.getChannel().sendMessage("|challenge").queue();
				}
			}

		}, new Command("spiel", "s") {
			public void execute(GuildMessageReceivedEvent e) {
				game_len = (cmd_body != null) ? Integer.parseInt(0 + cmd_body.replaceAll("\\D+", "")) : 0;
				teilnehmer = new ArrayList<User>();
				scores = new HashMap<User, Integer>();

				e.getChannel().sendMessage("|challenge").queue();

			}
		}, new Command("ergebnis", "e") {
			public void execute(GuildMessageReceivedEvent e) {

				if (game_len > 0) {
					e.getChannel().sendMessage("Das Spiel läuft doch noch!").queue();
				} else {
					if (teilnehmer.size() > 0) {

						User sieger = teilnehmer.get(0);

						for (int i = 1; i < teilnehmer.size(); i++) {
							if (scores.get(teilnehmer.get(i)) > scores.get(sieger)) {
								sieger = teilnehmer.get(i);
							}

						}

						e.getChannel().sendMessage(
								"Der Sieger ist: " + sieger.getName() + " mit einem Score von " + scores.get(sieger))
								.queue();
					}
					
					if(cmd_body.equals("genau")) {
						for(int i = 0; i < teilnehmer.size(); i++) {
							e.getChannel().sendMessage(teilnehmer.get(i).getName() + ": " + scores.get(teilnehmer.get(i))).queue();
						}
					}

					teilnehmer = null;
					scores = null;
				}
			}

		}, new Command("help", "h") {
			public void execute(GuildMessageReceivedEvent e) {
				e.getChannel().sendMessage("1. |random oder |r: random zitat").queue();
				e.getChannel().sendMessage("2. |challenge oder |c: frag ein Ratespiel an").queue();
				e.getChannel().sendMessage("3. |guess u oder |g u: rate den Urheber u").queue();
				e.getChannel().sendMessage("4. |spiel x oder |s x: starte ein Ratespiel mit x Fragen").queue();
				e.getChannel().sendMessage("5. |ergebnis oder |e: fordere die Ergebnisse eines abgeschlossenen Spiels")
						.queue();
			}

		}, new Command("del", "d") {
			public void execute(GuildMessageReceivedEvent e) {

			}

		}

		));
	}

	public Message random(Guild g, String name) {
		if (zitate.size() == 0) {
			zitate = g.getTextChannelsByName(name, true).get(0).getIterableHistory().complete();
		}

		Message msg = zitate.get((int) (Math.random() * zitate.size()));
		zitate.remove(msg);
		return msg;
	}

}
