package fruitymod;

import java.nio.charset.StandardCharsets;

import fruitymod.cards.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.abstracts.CustomUnlockBundle;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.SetUnlocksSubscriber;
import fruitymod.characters.TheSeeker;
import fruitymod.patches.AbstractCardEnum;
import fruitymod.patches.TheSeekerEnum;
import fruitymod.relics.Arcanosphere;

@SpireInitializer
public class FruityMod implements PostInitializeSubscriber,
	EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber,
	EditStringsSubscriber, SetUnlocksSubscriber, OnCardUseSubscriber {
	public static final Logger logger = LogManager.getLogger(FruityMod.class.getName());
	
    private static final String MODNAME = "FruityMod";
    private static final String AUTHOR = "Fruitstrike, ColdRain451, test447, fiiiiilth, & Pal";
    private static final String DESCRIPTION = "v0.4.3\n Adds The Seeker as a playable third character";
    
    private static final Color PURPLE = CardHelper.getColor(139.0f, 0.0f, 139.0f);
    private static final String FRUITY_MOD_ASSETS_FOLDER = "img";
    
    // card backgrounds
    private static final String ATTACK_PURPLE = "512/bg_attack_purple.png";
    private static final String SKILL_PURPLE = "512/bg_attack_purple.png";
    private static final String POWER_PURPLE = "512/bg_attack_purple.png";
    private static final String ENERGY_ORB_PURPLE = "512/card_purple_orb.png";
    
    private static final String ATTACK_PURPLE_PORTRAIT = "1024/bg_attack_purple.png";
    private static final String SKILL_PURPLE_PORTRAIT = "1024/bg_attack_purple.png";
    private static final String POWER_PURPLE_PORTRAIT = "1024/bg_attack_purple.png";
    private static final String ENERGY_ORB_PURPLE_PORTRAIT = "1024/card_purple_orb.png";
    
    // card images
    
    public static final String ANOMALY = "cards/anomaly.png";
    public static final String ARCANE_BARRAGE = "cards/starburst.png";
    public static final String ARCANE_TEMPEST = "cards/arcane_tempest.png";
    public static final String ARCANE_VOLLEY = "cards/irradiate.png";
    public static final String ARCHIVES = "cards/archives.png";
    public static final String ASTRAL_FORM = "cards/chaos_form.png";
    public static final String ASTRAL_HAZE = "cards/astral_haze.png";
    public static final String ASTRAL_SHIFT = "cards/dimension_door.png";
    public static final String BRAINSTORM = "cards/brainstorm.png";
    public static final String BRILLIANCE = "cards/brilliance.png";
    public static final String CHANNEL = "cards/channel.png";
    public static final String COALESCENCE = "cards/coalescence.png";
    public static final String COMET = "cards/comet.png";
    public static final String CORONA = "cards/corona.png";
    public static final String CREATIVITY = "cards/creativity.png";
    public static final String DEFEND_PURPLE = "cards/defend_purple.png";
    public static final String DEFLECTION_WARD = "cards/arcane_armor.png";
    public static final String ECHO = "cards/echo.png";
    public static final String ECLIPSE = "cards/eclipse.png";
    public static final String ENIGMA = "cards/enigma.png";
    public static final String ENTROPY = "cards/entropy.png";
    public static final String EQUINOX = "cards/equinox.png";
    public static final String ESSENCE_DART = "cards/phase_coil.png";
    public static final String ESSENCE_MIRROR = "cards/flicker.png";
    public static final String ESSENCE_SHRED = "cards/plasma_wave.png";
    public static final String ESSENCE_SPIKE = "cards/pulse_barrier.png";
    public static final String ETHER_BARRIER = "cards/nebula.png";
    public static final String ETHER_BLAST = "cards/ether_blast.png";
    public static final String ETHER_BOLT = "cards/essence_dart.png";
    public static final String EUREKA = "cards/eureka.png";
    public static final String EVENT_HORIZON = "cards/event_horizon.png";
    public static final String EXPERIMENT = "cards/experiment.png";
    public static final String FEEDBACK = "cards/feedback.png";
    public static final String FLARE = "cards/flare.png";
    public static final String FLOW = "cards/flow.png";
    public static final String FLUX_BLAST = "cards/null_storm.png";
    public static final String FLUX_BOLT = "cards/void_ray.png";
    public static final String FLUX_SHIELD = "cards/disruption_field.png";
    public static final String FORCE_SPIKE = "cards/unstable_orb.png";
    public static final String GRAVITY_WELL = "cards/gravity_well.png";
    public static final String HYPOTHESIS = "cards/hypothesis.png";
    public static final String ILLUMINATE = "cards/illuminate.png";
    public static final String IMPLOSION = "cards/implosion.png";
    public static final String MAGIC_MISSILE = "cards/magic_missile.png";
    public static final String MAGNETIZE = "cards/magnetize.png";
    public static final String METEOR_SHOWER = "cards/meteor_shower.png";
    public static final String MIND_OVER_MATTER = "cards/mind_over_matter.png";
    public static final String NEBULOUS_BLAST = "cards/force_ripple.png";
    public static final String NEXUS = "cards/nexus.png";
    public static final String NOVA = "cards/nova.png";
    public static final String PERIAPT_OF_CELERITY = "cards/periapt_of_celerity.png";
    public static final String PERIAPT_OF_POTENCY = "cards/periapt_of_potency.png";
    public static final String PERIAPT_OF_TENACITY = "cards/runic_binding.png";
    public static final String PERIAPT_OF_VIGOR = "cards/periapt_of_vigor.png";
    public static final String POWER_OVERWHELMING = "cards/power_overwhelming.png";
    public static final String POWER_SPIKE = "cards/overload.png";
    public static final String PROTECTION_WARD = "cards/disperse.png";
    public static final String REFLECTION_WARD = "cards/reflection_ward.png";
    public static final String RETROGRADE = "cards/retrograde.png";
    public static final String SHIMMER = "cards/shimmer.png";
    public static final String SIPHON_MAGIC = "cards/convergence.png";
    public static final String SIPHON_POWER = "cards/siphon_power.png";
    public static final String SIPHON_SPEED = "cards/siphon_speed.png";
    public static final String STRIKE_PURPLE = "cards/strike_purple.png";
    public static final String STROKE_OF_GENIUS = "cards/stroke_of_genius.png";
    public static final String SURGE = "cards/surge.png";
    public static final String SYZYGY = "cards/syzygy.png";
    public static final String THOUGHT_RAZE = "cards/thought_raze.png";
    public static final String TRANSFERENCE = "cards/transference.png";
    public static final String UMBRAL_BOLT = "cards/umbral_bolt.png";
    public static final String UMBRAL_WAVE = "cards/singularity.png";
    public static final String VACUUM = "cards/vacuum.png";
    public static final String VOID_BARRIER = "cards/genesis.png";
    public static final String VOID_BOLT = "cards/prismatic_sphere.png";
    public static final String VOID_RIPPLE = "cards/flux.png";
    public static final String VOID_SHACKLES = "cards/void_shackles.png";
    public static final String VORTEX = "cards/vortex.png";
    public static final String ZENITH = "cards/zenith.png";
    public static final String PHASE_COIL = "cards/phase_coil.png";
    
    // power images
    public static final String ASTRAL_HAZE_POWER = "powers/astral_haze.png";
    public static final String ESSENCE_MIRROR_POWER = "powers/essence_mirror.png";
    public static final String ETHEREALIZE_POWER = "powers/essence_mirror.png";
    public static final String ASTRAL_FORM_POWER = "powers/astral_form.png";
    public static final String VIGOR_POWER = "powers/vigor.png";
    public static final String ASTRAL_SHIFT_POWER = "powers/astral_shift.png";
    public static final String TENACITY_POWER = "powers/tenacity.png";
    public static final String CELERITY_POWER = "powers/celerity.png";
    public static final String POTENCY_POWER = "powers/potency.png";
    public static final String COALESCENCE_POWER = "powers/coalescence.png";
    public static final String CREATIVITY_POWER = "powers/creativity.png";
    public static final String POWER_OVERWHELMING_POWER = "powers/power_overwhelming.png";
    public static final String EVENT_HORIZON_POWER = "powers/event_horizon.png";
    public static final String ENIGMA_POWER = "powers/enigma.png";
    public static final String BRILLIANCE_POWER = "powers/brilliance.png";
    public static final String ANOMALY_POWER = "powers/anomaly.png";
    public static final String NEXUS_POWER = "powers/nexus.png";

    // relic images
    public static final String ARCANOSPHERE_RELIC = "relics/arcanosphere.png";
    
    // seeker assets
    private static final String SEEKER_BUTTON = "charSelect/seekerButton.png";
    private static final String SEEKER_PORTRAIT = "charSelect/seekerPortrait.jpg";
    public static final String SEEKER_SHOULDER_1 = "char/seeker/shoulder.png";
    public static final String SEEKER_SHOULDER_2 = "char/seeker/shoulder2.png";
    public static final String SEEKER_CORPSE = "char/seeker/corpse.png";
    public static final String SEEKER_SKELETON_ATLAS = "char/seeker/skeleton.atlas";
    public static final String SEEKER_SKELETON_JSON = "char/seeker/skeleton.json";
    
    // badge
    public static final String BADGE_IMG = "FRelicBadge.png";
    
    // texture loaders
    public static Texture getAstralHazePowerTexture() {
    	return new Texture(makePath(ASTRAL_HAZE_POWER));
    }
    
    public static Texture getEssenceMirrorPowerTexture() {
    	return new Texture(makePath(ESSENCE_MIRROR_POWER));
    }
    
    public static Texture getEtherealizePowerTexture() {
    	return new Texture(makePath(ETHEREALIZE_POWER));
    }
    
    public static Texture getAstralFormPowerTexture() {
    	return new Texture(makePath(ASTRAL_FORM_POWER));
    }
    
    public static Texture getVigorPowerTexture() {
    	return new Texture(makePath(VIGOR_POWER));
    }
    
    public static Texture getAstralShiftTexture() {
    	return new Texture(makePath(ASTRAL_SHIFT_POWER));
    }
    
    public static Texture getTenacityPowerTexture() {
    	return new Texture(makePath(TENACITY_POWER));
    }
    
    public static Texture getCelerityPowerTexture() {
    	return new Texture(makePath(CELERITY_POWER));
    }
    
    public static Texture getPotencyPowerTexture() {
    	return new Texture(makePath(POTENCY_POWER));
    }
    
    public static Texture getCoalescencePowerTexture() {
    	return new Texture(makePath(COALESCENCE_POWER));
    }
    
    public static Texture getCreativityPowerTexture() {
    	return new Texture(makePath(CREATIVITY_POWER));
    }
    
    public static Texture getPowerOverwhelmingPowerTexture() {
    	return new Texture(makePath(POWER_OVERWHELMING_POWER));
    }
    
    public static Texture getEventHorizonPowerTexture() {
    	return new Texture(makePath(EVENT_HORIZON_POWER));
    }
    
    public static Texture getEnigmaPowerTexture() {
    	return new Texture(makePath(ENIGMA_POWER));
    }
    
    public static Texture getArcanoSphereTexture() {
    	return new Texture(makePath(ARCANOSPHERE_RELIC));
    }
    
    public static Texture getBrillancePowerTexture() {
    	return new Texture(makePath(BRILLIANCE_POWER));
    }
    public static Texture getAnomalyPowerTexture() {
    	return new Texture(makePath(BRILLIANCE_POWER));
    }
    public static Texture getNexusPowerTexture() {
    	return new Texture(makePath(VIGOR_POWER));
    }
    
    /**
     * Makes a full path for a resource path
     * @param resource the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    public static final String makePath(String resource) {
    	return FRUITY_MOD_ASSETS_FOLDER + "/" + resource;
    }
    
    public FruityMod() {
    	logger.info("subscribing to postInitialize event");
        BaseMod.subscribeToPostInitialize(this);
        
        logger.info("subscribing to editCharacters event");
        BaseMod.subscribeToEditCharacters(this);
        
        logger.info("subscribing to editRelics event");
        BaseMod.subscribeToEditRelics(this);
        
        logger.info("subscribing to editCards event");
        BaseMod.subscribeToEditCards(this);

        logger.info("subscribing to editStrings event");
        BaseMod.subscribeToEditStrings(this);
        
        /* Disable this during playtesting for being counterproductive */
//        logger.info("subscribing to setUnlocks event");
//        BaseMod.subscribeToSetUnlocks(this);
        
        logger.info("subscribing to onCardUse event");
        BaseMod.subscribeToOnCardUse(this);
        
        /*
         * Note that for now when installing FruityMod, in the `mods/` folder another folder named
         * the value of FRUITY_MOD_ASSETS_FOLDER must be created into which all the contents of the
         * `images/` folder must be relocated
         */
        logger.info("creating the color " + AbstractCardEnum.PURPLE.toString());
        BaseMod.addColor(AbstractCardEnum.PURPLE.toString(),
        		PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE,
        		makePath(ATTACK_PURPLE), makePath(SKILL_PURPLE),
        		makePath(POWER_PURPLE), makePath(ENERGY_ORB_PURPLE),
        		makePath(ATTACK_PURPLE_PORTRAIT), makePath(SKILL_PURPLE_PORTRAIT),
        		makePath(POWER_PURPLE_PORTRAIT), makePath(ENERGY_ORB_PURPLE_PORTRAIT));
    }

    public static void initialize() {
    	logger.info("========================= FRUITYMOD INIT =========================");
		
		@SuppressWarnings("unused")
		FruityMod fruit = new FruityMod();
		
		logger.info("================================================================");
    }

    @Override
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture(makePath(BADGE_IMG));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addLabel("FruityMod does not have any settings (yet)!", 400.0f, 700.0f, (me) -> {});
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }

	@Override
	public void receiveEditCharacters() {
		logger.info("begin editting characters");
		
		logger.info("add " + TheSeekerEnum.THE_SEEKER.toString());
		BaseMod.addCharacter(TheSeeker.class, "The Seeker", "Seeker class string",
				AbstractCardEnum.PURPLE.toString(), "The Seeker",
				makePath(SEEKER_BUTTON), makePath(SEEKER_PORTRAIT),
				TheSeekerEnum.THE_SEEKER.toString());
		
		logger.info("done editting characters");
	}

	
	@Override
	public void receiveEditRelics() {
		logger.info("begin editting relics");
        
        // Add relics
        RelicLibrary.add(new Arcanosphere());
        
        logger.info("done editting relics");
	}

	@Override
	public void receiveEditCards() {
		logger.info("begin editting cards");
		
		logger.info("add cards for " + TheSeekerEnum.THE_SEEKER.toString());
		
		BaseMod.addCard(new Strike_Purple());
		BaseMod.addCard(new Defend_Purple());
		
		BaseMod.addCard(new ArcaneBarrage());
		BaseMod.addCard(new ArcaneVolley());
		BaseMod.addCard(new AstralHaze());
		BaseMod.addCard(new Brainstorm());
		BaseMod.addCard(new DeflectionWard());
		BaseMod.addCard(new Entropy());
		BaseMod.addCard(new EssenceDart());		
		BaseMod.addCard(new Flicker());
		BaseMod.addCard(new EssenceShred());		
		BaseMod.addCard(new PulseBarrier());
		BaseMod.addCard(new Nebula());
		BaseMod.addCard(new EtherBlast());
		BaseMod.addCard(new Flare());		
		BaseMod.addCard(new NullStorm());
		BaseMod.addCard(new VoidRay());
		BaseMod.addCard(new FluxShield());
		BaseMod.addCard(new ForceSpike());
		BaseMod.addCard(new Hypothesis());
		BaseMod.addCard(new Comet());
		BaseMod.addCard(new NebulousBlast());
		BaseMod.addCard(new PhaseCoil());
		BaseMod.addCard(new PowerSpike());
		BaseMod.addCard(new Syzygy());
		BaseMod.addCard(new SiphonPower());
		BaseMod.addCard(new Shimmer());
		BaseMod.addCard(new ThoughtRaze());
		BaseMod.addCard(new Retrograde());
		BaseMod.addCard(new UmbralWave());
		BaseMod.addCard(new UmbralBolt());
		BaseMod.addCard(new Genesis());
		BaseMod.addCard(new PrismaticSphere());
		BaseMod.addCard(new Flux());
		BaseMod.addCard(new Channel());
		BaseMod.addCard(new Implosion());
		BaseMod.addCard(new ChaosForm());
		BaseMod.addCard(new Vacuum());
		BaseMod.addCard(new AstralShift());
		BaseMod.addCard(new RunicBinding());
		BaseMod.addCard(new Eureka());
		BaseMod.addCard(new Eclipse());
		BaseMod.addCard(new Echo());
		BaseMod.addCard(new EventHorizon());
		BaseMod.addCard(new Zenith());
		BaseMod.addCard(new ReflectionWard());
		BaseMod.addCard(new Creativity());
		BaseMod.addCard(new Transference());
		BaseMod.addCard(new Surge());
		BaseMod.addCard(new StrokeOfGenius());
		BaseMod.addCard(new SiphonSpeed());
		BaseMod.addCard(new Convergence());
		BaseMod.addCard(new GravityWell());
		BaseMod.addCard(new Coalescence());
		BaseMod.addCard(new PeriaptOfCelerity());
		BaseMod.addCard(new PeriaptOfPotency());
		BaseMod.addCard(new MeteorShower());
		BaseMod.addCard(new PowerOverwhelming());
		BaseMod.addCard(new MindOverMatter());
		BaseMod.addCard(new ProtectionWard());
		BaseMod.addCard(new Magnetize());
		BaseMod.addCard(new Illuminate());
		BaseMod.addCard(new Flow());
		BaseMod.addCard(new Equinox());
		BaseMod.addCard(new Corona());
		BaseMod.addCard(new Archives());
		BaseMod.addCard(new MagicMissile());
		BaseMod.addCard(new Enigma());
		BaseMod.addCard(new Feedback());
		BaseMod.addCard(new Brilliance());
		BaseMod.addCard(new Anomaly());
		BaseMod.addCard(new Nova());
		BaseMod.addCard(new Vortex());
		BaseMod.addCard(new Nexus());
		
		logger.info("done editting cards");
	}

	@Override
	public void receiveEditStrings() {
		logger.info("begin editting strings");
		
        // RelicStrings
        String relicStrings = Gdx.files.internal("localization/FruityMod-RelicStrings.json").readString(
        		String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        // CardStrings
        String cardStrings = Gdx.files.internal("localization/FruityMod-CardStrings.json").readString(
        		String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
		
		logger.info("done editting strings");
	}

	@Override
	public void receiveSetUnlocks() {
		UnlockTracker.addCard("Brainstorm");
		UnlockTracker.addCard("VoidRay");
		UnlockTracker.addCard("Creativity");
		// seeker unlock 1
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				"Brainstorm", "VoidRay", "Creativity"
				), TheSeekerEnum.THE_SEEKER, 1);
		
		// seeker unlock 2
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				"Shimmer", "EtherBolt", "ChaosForm"
				), TheSeekerEnum.THE_SEEKER, 2);
		UnlockTracker.addCard("Shimmer");
		UnlockTracker.addCard("VoidRay");
		UnlockTracker.addCard("Creativity");
		
		// seeker unlock 3 (Vacuum tmp in place of Feedback)
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				"Transference", /*"Feedback", */
				"Vacuum", "Flicker"
				), TheSeekerEnum.THE_SEEKER, 3);
		UnlockTracker.addCard("Transference");
		/*UnlockTracker.addCard("Feedback");*/
		UnlockTracker.addCard("Vacuum");
		UnlockTracker.addCard("Flicker");
		
		// seeker unlock 4
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				"Zenith", "UmbralWave", "Flow"
				), TheSeekerEnum.THE_SEEKER, 4);
		UnlockTracker.addCard("Zenith");
		UnlockTracker.addCard("UmbralWave");
		UnlockTracker.addCard("Flow");
	}
	
	// used by fruitymod.patches.com.megacrit.cards.AbstractCard.CanUsedDazed
	public static boolean hasRelicCustom(String relicID, AbstractCard card) {
		System.out.println("I was checked!");
		// if it's checking for relicID.equals("Medical Kit") then we know we're in the block where
		// we are saying if we can use a status card so also check if we have enigma and the card is Dazed
		if (relicID.equals("Medical Kit") && AbstractDungeon.player.hasPower("EnigmaPower") && card.cardID.equals("Dazed")) {
			return true;
		} else {
			// otherwise leave normal behavior intact
			return AbstractDungeon.player.hasRelic(relicID);
		}
	}

	@Override
	public void receiveCardUsed(AbstractCard c) {
		AbstractPlayer p = AbstractDungeon.player;
		if (p.hasPower("EnigmaPower") && c.cardID.equals("Dazed")) {
			int stacks = p.getPower("EnigmaPower").amount;
			AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, stacks));
			AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(null, 
					DamageInfo.createDamageMatrix(stacks, true),
					DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
			c.exhaustOnUseOnce = true;
		}
	}
}