# SaveInstanceInBundle
Save and load fields to/from a [Bundle]

#Usage

   class AppActivity : AppCompatActivity() {
  
    @SaveInstance
    private var mV: Object? = Object()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LoadSaveInstanceBundle.save(outState, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoadSaveInstanceBundle.load(savedInstanceState, this)
    }

  }
  
  ** Feel free to use
